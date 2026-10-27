# SmartFind AIoT
**AI-Powered Lost & Found Hub with IoT-Enabled Collection Monitoring**

A university-campus lost-and-found project proposal and starter prototype for a Software Engineering & Project Management assignment.

## Repository contents
- `proposal/proposal.tex` — professional LaTeX proposal, including scope, requirements, process model, schedule, milestones, deliverables, risk plan, evaluation, and references.
- `diagrams/` — DFD, Use Case, Activity, ER, Class, and Object diagrams in Mermaid/PlantUML source formats.
- `docs/GANTT.md` — project schedule and Mermaid Gantt chart.
- `database/schema.sql` — initial MySQL schema.
- `java-interface/` — simple Java Swing interface demonstrating 7 domain/service classes.
- `ai-service/` — small Python Flask matching API (TF-IDF similarity baseline).
- `iot/esp8266_collection_point.ino` — ESP8266 HTTP intake-event example.
- `docs/TEST_PLAN.md` — starter test plan.
- `.gitignore` — ignores local secrets, build outputs, and Python virtual environments.

## Important academic note
This is an **initial proposal and starter prototype**, not a production-ready deployed system. The matching algorithm is a transparent baseline and must be evaluated with real, consented test data. The ESP8266 sketch needs a Wi-Fi network and an application endpoint configured before use. Human verification is mandatory before an item is released.

## Suggested setup
### Java interface
Requires JDK 17+.
```bash
cd java-interface
javac -d out src/smartfind/*.java
java -cp out smartfind.SmartFindApp
```

### Python AI service
Requires Python 3.10+.
```bash
cd ai-service
python -m venv .venv
# Windows: .venv\Scripts\activate
# Linux/macOS: source .venv/bin/activate
pip install -r requirements.txt
python app.py
```
Then test:
```bash
curl -X POST http://127.0.0.1:5001/match \
  -H "Content-Type: application/json" \
  -d '{"query":"black wallet with student card","candidates":[{"id":"F-101","text":"Found a dark leather wallet near the library with an ID card"}]}'
```

### MySQL
Run `database/schema.sql` in a development database. Configure credentials via environment variables in a future integration; never commit passwords.

### LaTeX proposal
Compile `proposal/proposal.tex` with a LaTeX distribution:
```bash
cd proposal
pdflatex proposal.tex
pdflatex proposal.tex
```
The proposal uses standard packages; the diagrams are provided separately as source files and can be exported to images for inclusion if desired.

## Before submitting
1. Replace all `[STUDENT NAME]`, `[STUDENT ID]`, `[TEACHER NAME]`, `[DEPARTMENT]`, and `[UNIVERSITY]` placeholders.
2. Confirm the schedule dates and semester requirements with your instructor.
3. Export diagrams to PNG/PDF and include them in the proposal if required.
4. Compile the LaTeX file and proofread the PDF.
5. Create a GitHub repository and push this folder; add the real repository URL to the proposal cover page and submission form.
