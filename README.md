# Sistema-de-Notifica-es-de-Pedidos-
Descrição do Projeto
Este projeto implementa um Sistema de Notificações de Pedidos para uma plataforma de e-commerce em Java, utilizando padrões de design para garantir flexibilidade e facilidade de manutenção. O sistema notifica diferentes partes do sistema a cada novo pedido, como o departamento de logística, o cliente e a equipe de marketing. Além disso, foi desenvolvido para possibilitar a adição de novas formas de notificação sem a necessidade de modificar o código já existente.


Sistema de Notificações de Pedidos em Java
Descrição do Projeto
Este projeto implementa um Sistema de Notificações de Pedidos para uma plataforma de e-commerce em Java, utilizando padrões de design para garantir flexibilidade e facilidade de manutenção. O sistema notifica diferentes partes do sistema a cada novo pedido, como o departamento de logística, o cliente e a equipe de marketing. Além disso, foi desenvolvido para possibilitar a adição de novas formas de notificação sem a necessidade de modificar o código já existente.

Requisitos Implementados
1. Padrão Factory:
Uma fábrica de notificações foi criada para gerar a implementação correta (e-mail, SMS ou push) com base no tipo de notificação solicitada.
A fábrica facilita a adição de novos tipos de notificações, como WhatsApp, sem alterar o código existente.
2. Padrão Singleton:
O serviço de gerenciamento de pedidos utiliza o padrão Singleton para garantir que apenas uma instância esteja ativa durante a execução da aplicação.
3. Padrão Adapter:
Um adaptador foi implementado para integrar o sistema com um sistema legado de parceiros, convertendo o formato de pedido para o formato esperado pelo sistema legado.
4. Padrão Observer:
O sistema utiliza o padrão Observer para notificar automaticamente logística, cliente e marketing quando um novo pedido é criado.
5. Injeção de Dependência (DI):
A injeção de dependência foi aplicada para injetar notificações diretamente no sistema de pedidos, em vez de criá-las internamente, facilitando a flexibilidade e a testabilidade do sistema.
Estrutura do Projeto
1. Classe Pedido:
Armazena as informações de um pedido, como ID, cliente, valor e status.
Responsável por criar pedidos e notificar os interessados.
2. Notificações:
Interface Notificacao: Define o método enviarNotificacao(Pedido pedido).
Implementações Concretas:
NotificacaoEmail: Envia uma notificação via e-mail.
NotificacaoSMS: Envia uma notificação via SMS.
NotificacaoPush: Envia uma notificação via push.
3. FabricaNotificacao (Factory):
Retorna a implementação correta de notificação com base no tipo (e-mail, SMS, push).
4. PedidoService (Singleton):
Gerencia a criação e processamento de pedidos, garantindo que haja apenas uma instância disponível durante a execução.
5. AdaptadorSistemaLegado (Adapter):
Converte o formato de pedidos para o padrão aceito pelo sistema legado, integrando-o com o sistema atual.
6. Observer - Notificadores:
Vários serviços (logística, cliente, marketing) observam o sistema de pedidos e são automaticamente notificados sempre que um novo pedido é criado.
Funcionalidades
Criação de Pedidos:

Um pedido pode ser criado com um cliente e status.
Após a criação, as partes interessadas (logística, cliente, marketing) são notificadas.
Notificações Flexíveis:

O sistema permite enviar notificações via e-mail, SMS, push e possibilita adicionar novos tipos, como WhatsApp, através da fábrica de notificações.
Integração com Sistema Legado:

Ao criar um pedido, o sistema pode enviar as informações para um sistema legado através do adaptador.

Possíveis Expansões
Integração com outras plataformas de notificação (e.g., Telegram, Slack).
Expansão do sistema para suportar múltiplos serviços legados.

Universidade Federal de Sergipe
Disciplina de Programação Orientada a Objetos
