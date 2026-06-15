# 📱 Mobile Automation — Appium + Java + Cucumber (BDD)

[![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Appium](https://img.shields.io/badge/Appium-9.x-662D91?logo=appium&logoColor=white)](https://appium.io/)
[![Cucumber](https://img.shields.io/badge/Cucumber-BDD-23D96C?logo=cucumber&logoColor=white)](https://cucumber.io/)
[![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![CI](https://github.com/Joselv1990/mobile-automation/actions/workflows/android-tests.yml/badge.svg)](../../actions)

Framework de automação de testes **mobile Android** construído com **Appium**, **Java 17**, **Cucumber (BDD/Gherkin)** e **Page Object Model**, com execução em **CI/CD via GitHub Actions**.

> 🇬🇧 *Android mobile test automation framework built with Appium, Java 17, Cucumber (BDD/Gherkin) and the Page Object Model, running in CI/CD with GitHub Actions. Scroll down for the English section.*

App sob teste: **[SauceLabs Swag Labs](https://github.com/saucelabs/sample-app-mobile)** — app de e-commerce de demonstração.

---

## 🇧🇷 Português

### ✨ Destaques técnicos

- **BDD com Cucumber + Gherkin** — cenários legíveis por negócio (`.feature`), reutilizáveis e versionados.
- **Page Object Model (POM)** — locators e ações encapsulados; testes desacoplados da UI.
- **Driver por thread (ThreadLocal)** — pronto para execução paralela.
- **Configuração externa** — `config.properties` sobrescrita por `-D` em runtime (device, versão, app).
- **Tags** — `@smoke`, `@regression`, `@negative` para filtrar execuções.
- **Screenshot automático** anexado ao relatório quando um cenário falha.
- **Relatórios** HTML + JSON do Cucumber.
- **CI/CD** — pipeline GitHub Actions com build rápido + execução em emulador Android.

### 🧱 Arquitetura

```
src/test/
├── java/com/joselv/mobile/
│   ├── core/        → ConfigReader, DriverFactory, DriverManager (ThreadLocal)
│   ├── pages/       → BasePage + Page Objects (LoginPage, ProductsPage)
│   ├── steps/       → Step Definitions (ligam Gherkin → Page Objects)
│   ├── hooks/       → Hooks (ciclo de vida do driver + screenshot)
│   └── runners/     → RunCucumberTest (JUnit Platform Suite)
└── resources/
    ├── features/    → Cenários BDD em Gherkin (.feature)
    └── config/      → config.properties
```

**Fluxo:** `Feature (.feature)` → `Step Definitions` → `Page Objects` → `Appium Driver` → `App Android`.

### ✅ Pré-requisitos

| Ferramenta        | Versão        |
|-------------------|---------------|
| JDK               | 17+           |
| Maven             | 3.9+          |
| Node.js           | 18+           |
| Appium            | 2.x           |
| Android SDK       | platform-tools + um emulador/dispositivo |

### 🚀 Como executar

**1. Instalar o Appium e o driver Android**

```bash
npm install -g appium
appium driver install uiautomator2
appium-doctor --android   # valida o ambiente (opcional)
```

**2. Baixar o app de teste**

```bash
# Linux/macOS
bash scripts/download-app.sh
```
```powershell
# Windows
powershell -ExecutionPolicy Bypass -File scripts/download-app.ps1
```

**3. Iniciar um emulador** (ou conectar um device com depuração USB)

```bash
adb devices   # confirme que o device aparece
```

**4. Subir o servidor Appium**

```bash
appium
```

**5. Rodar os testes**

```bash
# todos os cenários
mvn clean test

# apenas smoke
mvn clean test -Dtags="@smoke"

# device específico
mvn clean test -DdeviceName="Pixel_7_API_34" -DplatformVersion="14.0"
```

### 📊 Relatórios

Após a execução, abra:

```
target/cucumber-report/cucumber.html
```

### 🏷️ Tags disponíveis

| Tag           | Uso                                |
|---------------|------------------------------------|
| `@smoke`      | suíte mínima de fumaça             |
| `@regression` | regressão completa                 |
| `@negative`   | cenários de erro / borda           |
| `@login`      | feature de autenticação            |
| `@products`   | feature de carrinho de compras     |

### 🧪 Cenários cobertos

- Login com credenciais válidas → catálogo exibido
- Login bloqueado para `locked_out_user`
- Login inválido (Scenario Outline, data-driven)
- Adicionar 1 produto ao carrinho → badge = 1
- Adicionar múltiplos produtos → badge correto

---

## 🇬🇧 English

Android mobile automation framework using **Appium + Java 17 + Cucumber (BDD)** with the **Page Object Model** and **GitHub Actions** CI/CD.

**Highlights:** BDD/Gherkin scenarios, POM, ThreadLocal driver (parallel-ready), externalized configuration overridable at runtime, tag filtering, auto-screenshot on failure, HTML/JSON reports.

**Run:**

```bash
npm install -g appium && appium driver install uiautomator2
bash scripts/download-app.sh        # download the app under test
appium                              # start the server (in another terminal)
mvn clean test -Dtags="@smoke"      # run the smoke suite
```

Report: `target/cucumber-report/cucumber.html`

---

## 👤 Autor

**Jose Luis Vieira** — QA Engineer | Automation (Web, Mobile & API)
[GitHub](https://github.com/Joselv1990) · [LinkedIn](https://www.linkedin.com/in/jose-luis-vieira)

> Stack pessoal: Java · Selenium · Appium · Cucumber · RestAssured · CI/CD
