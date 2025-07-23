# 📡 externalAPI

Projeto Java baseado no **Spring Boot 3.5.3**, com foco em integração com APIs externas utilizando OpenFeign, versionamento de banco de dados com Flyway, monitoramento com Micrometer/Prometheus/Grafana, e documentação automática com Swagger.

---

## 🧰 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
  - Web
  - Data JPA
  - Actuator
  - Devtools
- **OpenFeign**: comunicação simplificada com APIs externas.
- **Springdoc OpenAPI**: documentação da API via Swagger UI.
- **Flyway**: versionamento e controle de migração de banco de dados.
- **PostgreSQL**: banco de dados relacional.
- **ShedLock**: agendamento seguro de tarefas com lock distribuído.
- **Micrometer + Prometheus + Grafana**: métricas e monitoramento.
- **Maven**: gerenciamento de dependências e build.

---

## 🚀 Executando o Projeto

```bash
mvn spring-boot:run
```

O serviço será iniciado em `http://localhost:8181`.

---

## 🔄 Versionamento de Banco de Dados

Gerenciado com **Flyway**.

- Scripts de migração devem ser colocados em: `src/main/resources/db/migration`
- Exemplo de script: `V1__create_table_users.sql`

```bash
mvn flyway:migrate
```

---

## 🗄️ Banco de Dados PostgreSQL

O projeto se comunica com banco de dados **PostgreSQL**, e já está preparado para isso via dependência `org.postgresql` e configuração com Spring Data JPA.

Para subir um container com PostgreSQL 14 com um banco `external_api`, execute:

```bash
docker run --name postgres-external-api -e POSTGRES_DB=external_api -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:14
```

---

## ⏱️ Schedules e Métodos Assíncronos

O sistema conta com **schedules automáticos** para execução de rotinas utilizando `@Scheduled`, e suporte para **métodos assíncronos** com `@Async`, garantindo melhor performance e escalabilidade.

---

## 📡 Comunicação com API Externa

A comunicação com serviços externos é realizada utilizando **Spring Cloud OpenFeign**, que permite criar clientes HTTP declarativos.

Exemplo:
```java
@FeignClient(name = "apiExterna", url = "https://api.exemplo.com")
public interface ApiExternaClient {
    @GetMapping("/dados")
    DadoDTO buscarDados();
}
```

---

## 📊 Monitoramento com Micrometer + Prometheus + Grafana

As métricas da aplicação estão disponíveis via **Spring Boot Actuator** com **Micrometer** e expostas para o **Prometheus**.

- Endpoint Prometheus:  
  `http://localhost:8181/actuator/prometheus`

- Integre com o Grafana para visualização em tempo real.

---

## 📖 Documentação da API (Swagger)

A documentação está disponível via **Springdoc OpenAPI**:

🔗 Acesse: [http://localhost:8181/swagger-ui/index.html#/](http://localhost:8181/swagger-ui/index.html#/)

---

## 🛠️ Build

```bash
mvn clean install
```

---

## 📦 Docker Compose

```yaml
version: '3'
services:
  prometheus:
    image: prom/prometheus
    ports:
      - "9090:9090"
    volumes:
      - ./prometheus.yml:/etc/prometheus/prometheus.yml
  grafana:
    image: grafana/grafana
    ports:
      - "3000:3000"
    volumes:
      - grafana-storage:/var/lib/grafana
    depends_on:
      - prometheus

volumes:
  grafana-storage:
```

---

## 📄 prometheus.yml

```yaml
global:
  scrape_interval: 10s

scrape_configs:
  - job_name: 'external-api'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['192.168.15.48:8181']
```

---

## 🧑‍💻 Desenvolvedor

- **Elias Neri**

---

## 📌 Observações

- Certifique-se de configurar corretamente as variáveis de ambiente para conexão com o banco PostgreSQL.
- O monitoramento via Grafana requer configuração prévia de dashboards.
- ShedLock depende da configuração de uma tabela específica no banco (geralmente chamada `shedlock`).