# SmartFind AIoT
### AI-Powered Lost & Found Hub with IoT-Enabled Collection Monitoring

**Course:** Software Engineering & Project Management  
**Project type:** Individual academic project  
**Status:** Initial proposal and prototype starter  
**Development approach:** Agile-inspired iterative process


SmartFind AIoT is a proposed university-campus lost-and-found platform that brings lost and found reports into one searchable system, recommends potentially matching reports using an AI-assisted text-matching service, and records physical item intake events through an ESP8266-based IoT collection point.

The system is designed to help students, staff, and authorized collection officers recover misplaced belongings more efficiently. *AI recommendations are advisory only: a person authorized by the institution must verify ownership before an item is handed over.*

---

## Table of Contents

- [1. Problem Statement](#1-problem-statement)
- [2. Proposed Solution](#2-proposed-solution)
- [3. Project Objectives](#3-project-objectives)
- [4. Main Features](#4-main-features)
- [5. Technology Stack](#5-technology-stack)
- [6. System Architecture](#6-system-architecture)
- [7. Repository Structure](#7-repository-structure)
- [8. Requirements](#8-requirements)
- [9. Run the Java Interface](#9-run-the-java-interface)
- [10. Run the Python AI Matching API](#10-run-the-python-ai-matching-api)
- [11. Set Up the MySQL Database](#11-set-up-the-mysql-database)
- [12. ESP8266 IoT Collection Point](#12-esp8266-iot-collection-point)
- [13. Project Proposal and UML/DFD Diagrams](#13-project-proposal-and-umldfd-diagrams)
- [14. Development Process](#14-development-process)
- [15. Schedule, Milestones, and Deliverables](#15-schedule-milestones-and-deliverables)
- [16. Testing and Evaluation](#16-testing-and-evaluation)
- [17. Security, Privacy, and Responsible Use](#17-security-privacy-and-responsible-use)
- [18. Current Limitations](#18-current-limitations)
- [19. Future Improvements](#19-future-improvements)
- [20. Academic Submission Checklist](#20-academic-submission-checklist)
- [21. GitHub Setup](#21-github-setup)
- [22. References](#22-references)

---

## 1. Problem Statement

University lost-and-found information is often scattered across informal conversations, social-media groups, and physical offices. Reports can be difficult to search, descriptions may use different words for the same object, and collection points may not maintain consistent digital intake records.

SmartFind AIoT proposes a centralized system to:

- Record lost and found reports in a consistent format.
- Help users discover potentially related reports.
- Record physical intake events from a designated collection point.
- Maintain report status and a traceable recovery workflow.
- Keep final ownership decisions under human control.

## 2. Proposed Solution

A user submits a lost or found report with a description, category, approximate location, date, and optional photograph. The Java application manages the report workflow and stores records in MySQL. A Python-based AI service compares descriptions and returns ranked candidate matches with similarity scores.

At a designated collection point, an ESP8266 device can send an intake event to the application. The application records the event after validating it. When someone claims an item, authorized staff verify the claim using suitable evidence and non-public item details, then record the handover.

### Typical workflow

1. A student reports a lost item or reports an item they found.
2. The application validates and stores the report.
3. The matching service ranks potentially related reports.
4. The user or collection officer reviews the recommendations.
5. A physical item is logged at a collection point, where applicable.
6. A claimant submits a claim.
7. Authorized staff verify ownership.
8. The system records the handover and updates the item status.

## 3. Project Objectives

1. Develop a centralized campus lost-and-found reporting concept.
2. Provide search and filtering for lost/found reports.
3. Demonstrate AI-assisted matching of report descriptions.
4. Demonstrate IoT-based intake-event reporting using an ESP8266.
5. Design a MySQL data model for reports, users, matches, collection points, intake events, claims, and handovers.
6. Apply a software process model and produce project schedules, milestones, and deliverables.
7. Create the required design diagrams and a Java interface using classes represented in the class diagram.
8. Evaluate the prototype and document its limitations.

## 4. Main Features

| Feature | Description | Initial status |
|---|---|---|
| Lost/found reports | Create reports with type, category, description, and location | Java demo UI |
| Candidate matching | Rank descriptions by text similarity | Python API and Java baseline |
| Collection point | Represent a physical campus intake location | Domain class and SQL schema |
| IoT intake events | ESP8266 sends event data over Wi-Fi | Firmware example; endpoint integration required |
| Database | Store users, reports, matches, events, claims, and handovers | SQL schema provided |
| Claim verification | Staff review before an item is returned | Domain model and schema; full workflow not integrated |
| Project documentation | Proposal, diagrams, schedule, and test plan | Included as source files |

## 5. Technology Stack

| Layer | Technology | Purpose |
|---|---|---|
| Desktop prototype | Java Swing | Demonstrate the initial interface |
| Application/domain logic | Java | Model reports, users, matches, and intake events |
| Database | MySQL | Persistent storage design |
| AI matching service | Python, Flask, scikit-learn | Text vectorization and similarity ranking |
| IoT hardware | ESP8266 | Send collection-point intake events |
| Communication | HTTP/JSON prototype | Exchange matching or intake-event data |
| Documentation | LaTeX, Markdown, Mermaid, PlantUML | Proposal, diagrams, and schedule |
| Version control | Git and GitHub | Source control and submission |

## 6. System Architecture

mermaid
flowchart TD
    U[Student / Staff] --> UI[Java Interface and Application Layer]
    UI --> DB[(MySQL Database)]
    UI --> AI[Python Flask Matching API]
    AI --> UI
    ESP[ESP8266 Collection Point] -->|HTTP / JSON intake event| API[Application Intake Endpoint]
    API --> DB
    UI --> V[Authorized Human Verification]
    V --> DB

### Component responsibilities

- *Java application:* report workflow, validation, business rules, and user-facing interaction.
- *MySQL:* persistent records and relationships.
- *Python AI service:* text normalization, TF-IDF vectorization, cosine similarity, and ranked suggestions.
- *ESP8266:* sends an intake event from a collection point over Wi-Fi.
- *Authorized staff:* assess evidence, approve or reject claims, and record handover.

**Integration note:** The current Java demo, Python matching API, MySQL schema, and ESP8266 sketch are separate starter components. They are not yet connected as a complete production system.


## 7. Repository Structure

text
SmartFind-AIoT/
├── README.md
├── .gitignore
│
├── proposal/
│   └── proposal.tex
│
├── diagrams/
│   ├── DFD.md
│   ├── USE_CASE.md
│   ├── ACTIVITY.md
│   ├── ER_DIAGRAM.md
│   ├── CLASS_DIAGRAM.md
│   └── OBJECT_DIAGRAM.md
│
├── docs/
│   ├── GANTT.md
│   └── TEST_PLAN.md
│
├── database/
│   └── schema.sql
│
├── java-interface/
│   └── src/
│       └── smartfind/
│           ├── User.java
│           ├── ItemReport.java
│           ├── MatchCandidate.java
│           ├── CollectionPoint.java
│           ├── IntakeEvent.java
│           ├── Claim.java
│           ├── SmartFindService.java
│           └── SmartFindApp.java
│
├── ai-service/
│   ├── app.py
│   └── requirements.txt
│
└── iot/
    └── esp8266_collection_point.ino

## 8. Requirements

### Software

- Java Development Kit (JDK) 17 or later
- Python 3.10 or later
- pip
- MySQL Server 8.x recommended
- Git
- A LaTeX distribution such as TeX Live or MiKTeX to compile the proposal
- Arduino IDE with ESP8266 board support for hardware testing

### Hardware (for IoT demonstration)

- ESP8266 development board (for example, NodeMCU ESP8266)
- USB cable and suitable power source
- Wi-Fi network reachable by the configured application endpoint
- Optional enclosure and physical intake trigger, such as a button or scanner

The Java UI and Python API can be demonstrated without the ESP8266 hardware. The SQL schema can also be reviewed independently.

## 9. Run the Java Interface

The Java Swing prototype stores reports in memory for the current run. It does not yet read or write to MySQL.

From the repository root:

cd java-interface
javac -d out src/smartfind/*.java
java -cp out smartfind.SmartFindApp

### Using the interface

1. Select LOST or FOUND.
2. Enter a category, description, and approximate location.
3. Select *Submit report*.
4. Add another report of the opposite type with overlapping words.
5. Select a report and use *Find possible matches* to display suggestions for the latest report submitted.

The prototype uses a simple word-overlap score in Java. This is a demonstration baseline and is separate from the TF-IDF-based Python API.

## 10. Run the Python AI Matching API

### Create a virtual environment

From the repository root:

*Windows PowerShell*
cd ai-service
python -m venv .venv
.\.venv\Scripts\Activate.ps1
pip install -r requirements.txt
python app.py

*Linux / macOS*
cd ai-service
python3 -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
python app.py

The API listens locally at http://127.0.0.1:5001.

### Health check

Open this URL in a browser:

text
http://127.0.0.1:5001/health

Expected response:

{
  "service": "SmartFind matching baseline",
  "status": "ok"
}

### Test matching with curl

curl -X POST http://127.0.0.1:5001/match \
  -H "Content-Type: application/json" \
  -d '{
    "query": "black wallet with student card near library",
    "candidates": [
      {
        "id": "FOUND-101",
        "text": "Found a dark leather wallet with an ID card near the central library"
      },
      {
        "id": "FOUND-102",
        "text": "Blue water bottle found at the sports ground"
      }
    ]
  }'

The response contains ranked candidates and a similarity score between 0 and 1. A higher score means greater text similarity within this baseline; it does *not* prove that two reports refer to the same object.

### API summary

| Method | Endpoint | Purpose |
|---|---|---|
| GET | /health | Check whether the service is running |
| POST | /match | Rank candidate descriptions against a query |

Example request body:

{
  "query": "description of the lost item",
  "candidates": [
    {"id": "F-101", "text": "description of a found item"},
    {"id": "F-102", "text": "another found item description"}
  ]
}

## 11. Set Up the MySQL Database

1. Start MySQL Server.
2. Open MySQL Workbench or a MySQL command-line client.
3. Run the script at database/schema.sql.

Example:

mysql -u YOUR_DB_USER -p < database/schema.sql

The script creates a database named smartfind_aiot and the following tables:

| Table | Purpose |
|---|---|
| users | User profile, role, and password hash |
| item_reports | Lost/found reports and their status |
| match_candidates | Similarity suggestions and review status |
| collection_points | Registered physical collection points/devices |
| intake_events | Intake-event records and event identifiers |
| claims | Claims and ownership-verification status |
| handovers | Confirmed item handover records |

*Database note:* This schema is a starting design. Application code for database connectivity, authentication, authorization, migrations, and transactional handover processing must be implemented and tested before real use. Never commit database passwords or real personal data to GitHub.

## 12. ESP8266 IoT Collection Point

Firmware source: iot/esp8266_collection_point.ino

### Intended behavior

- Connect to a Wi-Fi network.
- Create a sample event identifier.
- Send a JSON intake event to a configured HTTP endpoint.
- Print the HTTP status to the serial monitor.

### Before uploading

1. Open the .ino file in Arduino IDE.
2. Install ESP8266 board support if it is not already installed.
3. Select the correct ESP8266 board and COM port.
4. Configure WIFI_SSID, WIFI_PASSWORD, and SERVER_URL.
5. Replace the demo report ID and periodic event trigger with the desired test setup.
6. Upload and open Serial Monitor at 115200 baud.

Example event payload:

{
  "event_id": "sf-esp-01-demo-0001",
  "device_id": "sf-esp-01",
  "report_id": 502,
  "event_type": "ITEM_RECEIVED"
}

### Important hardware limitation

The repository does *not* yet include a working Java/server endpoint at the example SERVER_URL. The firmware cannot complete an end-to-end intake demonstration until a receiver endpoint is implemented and the URL is configured. For a real deployment, add device authentication, HTTPS, server-side validation, event deduplication, safe retry behavior, and secure credential storage. The demo event ID generator is not guaranteed to be globally unique after reboots.

## 13. Project Proposal and UML/DFD Diagrams

### LaTeX proposal

Main file: proposal/proposal.tex

Compile twice to update references and the table of contents:

cd proposal
pdflatex proposal.tex
pdflatex proposal.tex

Replace all placeholders such as [UNIVERSITY NAME], [STUDENT NAME], [STUDENT ID], [COURSE CODE], [TEACHER NAME], [DATE], and [GITHUB URL] before submission.

### Diagram source files

| Diagram | File |
|---|---|
| Data Flow Diagram | diagrams/DFD.md |
| Use Case Diagram | diagrams/USE_CASE.md |
| Activity Diagram | diagrams/ACTIVITY.md |
| Entity Relationship Diagram | diagrams/ER_DIAGRAM.md |
| Class Diagram | diagrams/CLASS_DIAGRAM.md |
| Object Diagram | diagrams/OBJECT_DIAGRAM.md |

The DFD and ERD use Mermaid syntax; the UML diagrams use PlantUML. Render them using compatible Mermaid or PlantUML tools. If the instructor expects all diagrams inside the final PDF, export each diagram to an image or PDF and include it in the LaTeX proposal before compiling.

## 14. Development Process

The proposal uses an *Agile-inspired iterative process*. The work is divided into small stages so that requirements, design, implementation, integration, testing, and documentation can be reviewed throughout the project.

1. *Iteration 1 — Initiation and requirements:* define the problem, stakeholders, feasibility, scope, and requirements.
2. *Iteration 2 — Design:* create architecture, data model, diagrams, API contracts, and interface plan.
3. *Iteration 3 — Core prototype:* implement Java domain classes and interface; prepare the database schema.
4. *Iteration 4 — AI matching:* implement and test the Python API.
5. *Iteration 5 — IoT and integration:* configure the ESP8266 event sender and develop the receiving endpoint.
6. *Iteration 6 — Verification and handoff:* test the system, assess privacy and usability, fix defects, and prepare the final demonstration.

Track work in GitHub Issues or a project board. Each task should have acceptance criteria and be marked complete only after review or testing.

## 15. Schedule, Milestones, and Deliverables

Detailed schedule: docs/GANTT.md

The current plan is an illustrative 12-week schedule beginning *2 November 2026*. Adjust dates to match the instructor's actual deadline.

| Milestone | Target | Main deliverables |
|---|---|---|
| M1 — Requirements baseline | End of Week 2 | Problem statement, scope, requirements, feasibility, initial risks |
| M2 — Design baseline | End of Week 4 | Architecture, DFD, five UML/ER artifacts, data/API design |
| M3 — UI and schema prototype | End of Week 6 | Java interface, domain classes, MySQL schema |
| M4 — AI matching demo | End of Week 8 | Flask matching API and test cases |
| M5 — IoT intake demo | End of Week 10 | ESP8266 event format, receiver endpoint, integration evidence |
| M6 — Final verification | End of Week 12 | Test results, evaluation, updated proposal/report, demo |

### Deliverable checklist

- [x] Initial project proposal in LaTeX source
- [x] DFD source
- [x] Use Case Diagram source
- [x] Activity Diagram source
- [x] ER Diagram source
- [x] Class Diagram source
- [x] Object Diagram source
- [x] Gantt chart and milestone plan
- [x] Java interface source with more than five classes
- [x] MySQL schema
- [x] Python matching API starter
- [x] ESP8266 firmware starter
- [x] Initial test plan
- [ ] Replace academic placeholders and confirm course details
- [ ] Render all diagrams and include them in the final proposal if required
- [ ] Compile and proofread the final proposal PDF
- [ ] Implement the missing application and IoT integrations
- [ ] Run tests and record actual results
- [ ] Add the final GitHub repository URL to the proposal

## 16. Testing and Evaluation

Test plan: docs/TEST_PLAN.md

Suggested evaluation areas:

- *Functional correctness:* valid reports can be created; invalid input is rejected.
- *Matching quality:* manually judge top-ranked results and calculate precision@3 if a labelled dataset is available.
- *Performance:* measure local API response times over repeated test requests.
- *IoT reliability:* count valid intake events acknowledged and test duplicate event handling.
- *Privacy and security:* confirm private contact/evidence fields are not exposed in public views.
- *Usability:* ask a small, approved group of volunteers to complete basic reporting and searching tasks.

Record the dataset size, test environment, results, false positives, and limitations. Do not invent or claim test results before the tests have actually been run.

## 17. Security, Privacy, and Responsible Use

- Collect only the information needed to recover an item.
- Do not publish phone numbers, student IDs, private evidence, or sensitive details in public reports.
- Obtain appropriate consent before using photographs.
- Restrict claim verification and handover actions to authorized staff.
- Store password hashes, not plaintext passwords.
- Keep secrets in environment configuration, not source files.
- Validate all user input and all IoT event payloads on the server.
- Use HTTPS and device authentication for a real deployment.
- Define a retention policy for reports, photographs, and claims.
- Treat AI scores as suggestions; do not use them as proof of ownership.
- Use fictional or consented data for academic demonstrations.

## 18. Current Limitations

This repository is an initial academic project package. It is not a finished production deployment.

- The Java Swing UI stores data in memory and loses it when the application closes.
- The Java matching demonstration uses word overlap, not the Python TF-IDF model.
- The Python matching API is independent and is not yet called by the Java UI.
- The MySQL schema is provided, but database integration is not implemented in the Java prototype.
- The ESP8266 sketch needs a receiving endpoint and configuration before it can send successful events.
- Authentication, authorization enforcement, production logging, image uploads, and notifications are not fully implemented.
- The diagrams describe the intended system and may need revision as implementation decisions change.
- The LaTeX proposal is source; compile and review the PDF before submission.

## 19. Future Improvements

- Integrate the Java application with MySQL.
- Connect Java to the Python matching API through a documented HTTP interface.
- Implement an authenticated intake-event endpoint with idempotency.
- Add role-based login for students, staff, and administrators.
- Add secure image upload and controlled image display.
- Improve matching using category, location, dates, synonyms, and optional image similarity.
- Add notifications when a plausible match appears.
- Add a staff dashboard for claims, collection points, and audit logs.
- Improve device event retries and offline buffering.
- Conduct a larger, approved usability and matching-quality evaluation.

## 20. Academic Submission Checklist

Before submitting the assignment:

- [ ] Add your name, student ID, department, university, course code, instructor, and date.
- [ ] Confirm the project title and scope with your instructor.
- [ ] Compile proposal/proposal.tex successfully.
- [ ] Proofread the PDF, page numbers, table of contents, and references.
- [ ] Ensure all six diagrams are legible and included in the required format.
- [ ] Confirm that the class diagram corresponds to the Java classes.
- [ ] Verify the Gantt schedule, milestones, and deliverables.
- [ ] Run the Java prototype and Python matching API.
- [ ] Test the SQL schema in a development database if available.
- [ ] Clearly distinguish completed work from planned work.
- [ ] Push the project to GitHub and paste the real repository link into the proposal.
- [ ] Never commit passwords, private keys, or real student personal data.

## 21. GitHub Setup

Create a repository on GitHub named SmartFind-AIoT, then open a terminal in this project folder.

git init
git add .
git commit -m "Initial SmartFind AIoT project"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/SmartFind-AIoT.git
git push -u origin main

Replace YOUR_USERNAME with your GitHub username. If the repository already contains a README or license created on GitHub, follow the appropriate Git steps to reconcile the initial histories rather than blindly overwriting files.

### Suggested repository description

AI-powered campus lost-and-found project with text-similarity matching, ESP8266 collection-point event monitoring, Java prototype, MySQL schema, and software engineering documentation.


### Suggested GitHub topics

aiot
lost-and-found
java
python
flask
mysql
esp8266
software-engineering
uml
university-project

## 22. References

1. Pressman, R. S., and Maxim, B. R. Software Engineering: A Practitioner's Approach, 9th edition, McGraw-Hill, 2019.
2. Sommerville, I. Software Engineering, 10th edition, Pearson, 2015.
3. Flask documentation: https://flask.palletsprojects.com/
4. scikit-learn documentation: https://scikit-learn.org/stable/
5. MySQL documentation: https://dev.mysql.com/doc/
6. Espressif ESP8266 documentation: https://www.espressif.com/en/products/socs/esp8266

---

## Project Summary

SmartFind AIoT proposes a practical campus lost-and-found workflow by combining report management, AI-assisted candidate matching, and IoT-based collection-point event logging. This repository provides the initial proposal, modeling artifacts, project schedule, database design, Java UI prototype, Python matching API, ESP8266 firmware example, and test plan.

*The final goal is not to let AI decide who owns an item. The goal is to help people find likely matches faster while keeping verification, privacy, and item handover under responsible human control.*

