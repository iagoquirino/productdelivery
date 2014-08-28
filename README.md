productdelivery
==================
Product Delivery APP é um aplicativo que a partir de rotas definidas por usuários consegue traçar o melhor caminho para entrega de mercadorias.

Para a lógica de melhor rota foi baseado nos famosos algoritmos.

Algoritmo de Dijkstra - http://pt.wikipedia.org/wiki/Algoritmo_de_Dijkstra

Algoritmo de Bellman Ford - http://pt.wikipedia.org/wiki/Algoritmo_de_Bellman-Ford

Problema do caixeiro-viajante - http://pt.wikipedia.org/wiki/Problema_do_caixeiro-viajante

Conforme a origem e destino informado pelo usuário é feito uma verificação de todas as possíbilidades de rotas, realizando uma somatória de toda a distancia, onde a escolha é baseada na menor somatória.

Após realizado a verificação é feito o calculo do custo do trajeto com base na autonomia do veículo e o valor do combustivel também informado pelo usuário.
Retornando um JSON para o usuário com todos os pontos e o custo do trajeto.

**Para execução do projeto:**

JDK 1.6+ - http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk7-downloads-1880260.html

PostGres - http://www.postgresql.org/download/

Apache Maven 3.0.4+ - http://maven.apache.org/download.cgi

Apache Tomcat 7 - http://tomcat.apache.org/download-70.cgi

**API´S**

Para informações de API´S acesse o WIKI.

https://github.com/iquirino88/productdelivery/wiki/API%C2%B4S
