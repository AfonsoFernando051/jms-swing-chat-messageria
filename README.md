# 📂 Trabalho de Desenvolvimento de Sistemas Distribuídos

**Tema:** Chat com Mensageria Utilizando JMS (Java Message Service)
**Curso:** Ciência da Computação
**Disciplina:** Desenvolvimento de Sistemas Distribuídos
**Professor:** Flávio Velloso Laper
**Alunos:** Fernando Afonso de Souza Dias, Caio Souza Silva
**Data:** Junho de 2025

---

## 💼 Objetivo

Implementar um sistema de chat rudimentar com comunicação baseada em mensageria, utilizando a especificação JMS (Java Message Service) e o broker ActiveMQ.

---

## ⚙️ Tecnologias Utilizadas

* Java 17
* Swing (interface gráfica)
* Apache ActiveMQ 5.17.6
* Maven (gestão de dependências e build)

---

## 🚀 Execução do Sistema

### 1. Inicializar o ActiveMQ

1. Baixe a versão **5.17.6** do ActiveMQ em: [https://activemq.apache.org/components/classic/download/](https://activemq.apache.org/components/classic/download/)
2. Extraia o arquivo em uma pasta, por exemplo: `C:\apache-activemq-5.17.6`
3. No Git Bash ou Prompt de Comando, execute:

   ```bash
   cmd.exe /c "activemq.bat start"
   ```
4. Acesse o painel de controle do ActiveMQ em: [http://localhost:8161](http://localhost:8161)

   * **Usuário:** `admin`
   * **Senha:** `admin`

---

### 2. Compilar e Rodar o Projeto

1. No terminal, navegue até a raiz do projeto e execute:

```bash
mvn clean package
java -jar target/dsd-0.0.1-SNAPSHOT.jar
```

2. Alternativamente, no Eclipse:

   * Clique com o botão direito na classe `ChatClientUI.java`
   * Escolha **Run As > Java Application**

---

### 3. Testar o Chat

1. Abra **duas instâncias** do programa.
2. Informe códigos de usuário diferentes (ex: `Ana` e `Bruno`).
3. Em cada janela, clique em `Contato > Adicionar Contato` e adicione o outro usuário.
4. Troque mensagens normalmente.

---

## 📄 Observações

* O broker ActiveMQ é responsável por intermediar todas as mensagens.
* Cada cliente é identificado por um código único fornecido ao iniciar o programa.
* As mensagens podem ser **privadas** (direcionadas) ou **públicas** (broadcast, se configurado).

---

> Projeto desenvolvido como parte da atividade auto-instrucional da disciplina de Sistemas Distribuídos - Universidade FUMEC.
