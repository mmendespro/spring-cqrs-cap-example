# CQRS

O padrão CQRS (Command Query Responsibility Segregation) é uma abordagem de design de software que separa a leitura e a escrita de dados em diferentes modelos.

![cqrs-pattern.png](./img/cqrs-pattern.png)

## O que é CQRS?

- **Command**: Representa operações que alteram o estado do sistema.
- **Query**: Representa operações que retornam dados sem modificar o estado.

## Benefícios do CQRS:

1. **Escalabilidade**: Permite escalar a leitura e a escrita de forma independente.
2. **Modelagem mais clara**: Separa preocupações de leitura e escrita, facilitando a manutenção.
3. **Desempenho**: Otimiza operações de leitura e escrita de acordo com suas necessidades específicas.

## Implementação do CQRS:

Para o presente exemplo teremos duas bases de dados, uma irá guardar os eventos de mudança, ou seja, os resultados do processamento de um comando e a outra irá guardar os dados em uma base relacional a fim de realizarmos consultas naturais, com selects, joins, etc.

Lembrando sempre que:
- **Comandos**: Alteram o estado do sistema.
- **Consultas**: Retornam dados sem modificar o estado.
- **Modelos separados**: Um para leitura e outro para escrita.

As operações de Sync entre os modelos de dados (query model e command model) podem ser feitas de muitas formas, desde o uso de CDC (Change Data Capture), ou Usando o Transactional Outbox Pattern, ou da forma simplória como foi abordada neste exemplo.

## Teorema de CAP (Consistency, Availability, Partition Tolerance)

![cap-theorem.png](./img/cap-theorem.png)

Em sistemas distribuídos, é comum enfrentar o desafio de manter a consistência dos dados, especialmente ao adotar o paradigma do CQRS, que requer lidar com bases otimizadas para diferentes operações.

O Teorema CAP afirma que em um sistema distribuído, é impossível garantir simultaneamente consistência, disponibilidade e tolerância a partições. Um sistema distribuído só pode garantir duas dessas propriedades ao mesmo tempo, sendo necessário fazer trade-offs entre elas.

## Conclusão

A motivação por tras deste exemplo é fazer uma abordagem poderosa para lidar com operações de leitura e escrita de forma eficiente e escalável. Separando responsabilidades, e melhorando a manutenção e o desempenho do sistema.