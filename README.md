# Rate Analyzer

This service analyzes currency exchange rate changes based on user preferences and processes the data accordingly.

## Features

### Currency Rate Analysis

- The service tracks currency exchange rate fluctuations and analyzes changes according to user-defined preferences.
- It fetches actual rates via OpenFeign client.

### Weekly Statistics

- The service generates and provides weekly statistics for currencies monitored by users.
- Users receive summarized reports on currency trends and changes over time.

### User Preferences Management

- The service allows users to set their currency monitoring preferences through a REST API.
- Preferences are stored and utilized to tailor analysis and reporting to individual users.

### Monitoring & Logs

- The service is integrated with the ELK stack (Elasticsearch, Logstash, and Kibana) for comprehensive monitoring and log aggregation.
- Prometheus and Grafana are configured to query historical metrics, monitor trends over time, and set up alerts based on custom rules.