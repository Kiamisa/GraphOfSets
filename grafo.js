// Importa a biblioteca D3.js
const d3 = require('d3');

// Lê o arquivo de texto com as informações do grafo
d3.text('grafoFinal.txt').then(function(text) {
   // Separa as linhas do arquivo de texto em um array
   const lines = text.split('\n');

   // Obtém a primeira linha do arquivo de texto, que contém informações sobre o grafo
   const info = lines.shift().split(' ');

   // Verifica se o grafo é dirigido ou não dirigido
    const isDirected = info[0] === 'D';
    const isUndirected = info[0] === 'ND';

    // Cria um objeto para armazenar os nós do grafo
    const nodes = {};

   // Percorre todas as linhas do arquivo de texto para criar as conexões entre os nós
  lines.forEach(function(line) {
    // Remove os parênteses e separa os números
    const [source, target] = line.replace(/[()]/g, '').split(',');

    // Adiciona os nós na lista de nós, se ainda não estiverem lá
    nodes[source] = { id: source };
    nodes[target] = { id: target };

    // Cria a conexão entre os nós
    if(isUndirected){
    const link = { source: source, target: target }
    link = { source: target, target: source }
    }else{
      link = { source: source, target: target }
    }

    // Adiciona a conexão na lista de conexões
    link.push(link);
  });

    // Cria o elemento SVG para desenhar o grafo
    const svg = d3.select('body').append('svg')
    .attr('width', 500)
    .attr('height', 500);

    // Cria um elemento para armazenar as conexões
    const linkGroup = svg.append('g').attr('class', 'links');

    // Cria um elemento para armazenar os nós
    const nodeGroup = svg.append('g').attr('class', 'nodes');

    // Cria um layout de força para posicionar os nós
    const simulation = d3.forceSimulation()
    .force('link', d3.forceLink().id(function(d) { return d.id; }))
    .force('charge', d3.forceManyBody())
    .force('center', d3.forceCenter(250, 250));

    // Adiciona as conexões no SVG
    const link = linkGroup.selectAll('line')
    .data(links)
    .enter().append('line')
    .attr('stroke', '#999')
    .attr('stroke-opacity', 0.6)
    .attr('stroke-width', function(d) { return Math.sqrt(d.value); });

    // Adiciona os nós no SVG
    const node = nodeGroup.selectAll('circle')
    .data(d3.values(nodes))
    .enter().append('circle')
    .attr('r', 5)
    .attr('fill', '#fff')
    .attr('stroke', '#999')
    .attr('stroke-width', 1.5)

    // Adiciona o texto do nó no SVG
    const label = nodeGroup.selectAll('text')
    .data(d3.values(nodes))
    .enter().append('text')
    .text(function(d) { return d.id; })
    .attr('dx', 12)
    .attr('dy', '.35em')
    .style('font-size', 12);

    // Adiciona as setas nas conexões direcionadas, caso seja direcionado
  if (isDirected) {
    link.attr('marker-end', 'url(#arrowhead)');
    svg.append('defs').append('marker')
    .attr('id', 'arrowhead')
    .attr('viewBox', '-0 -5 10 10')
    .attr('refX', 17)
    .attr('refY', 0)
    .attr('orient', 'auto')
    .attr('markerWidth', 10)
    .attr('markerHeight', 10)
    .attr('xoverflow', 'visible')
    .append('svg:path')
    .attr('d', 'M 0,-5 L 10 ,0 L 0,5')
    .attr('fill', '#999')
    .attr('stroke', '#999');
  }
    
    // Executa o layout de força para posicionar os nós
    simulation.nodes(d3.values(nodes)).on('tick', ticked);
    simulation.force('link').links(links);
    
  function ticked() {
    link
    .attr('x1', function(d) { return d.source.x; })
    .attr('y1', function(d) { return d.source.y; })
    .attr('x2', function(d) { return d.target.x; })
    .attr('y2', function(d) { return d.target.y; });
    node
    .attr('cx', function(d) { return d.x; })
    .attr('cy', function(d) { return d.y; });

    label
    .attr('x', function(d) { return d.x; })
    .attr('y', function(d) { return d.y; });
  }
});