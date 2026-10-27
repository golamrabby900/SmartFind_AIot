# Project Completion Schedule — SmartFind AIoT

**Process model:** Agile-inspired iterative development  
**Duration:** 12 weeks (relative schedule; align to actual course calendar)

## Gantt chart

```mermaid
gantt
    title SmartFind AIoT — 12 Week Project Plan
    dateFormat  YYYY-MM-DD
    axisFormat  %d %b
    section Initiation
    Problem and stakeholder analysis :a1, 2026-11-02, 5d
    Feasibility and scope             :a2, after a1, 5d
    Requirements baseline (M1)        :milestone, m1, after a2, 0d
    section Design
    Architecture and API contracts    :b1, 2026-11-16, 5d
    DFD and UML/ER diagrams           :b2, after b1, 5d
    UI and database design (M2)       :milestone, m2, after b2, 0d
    section Core implementation
    Java interface and domain classes :c1, 2026-11-30, 10d
    MySQL schema and validation       :c2, after c1, 5d
    UI and schema demo (M3)            :milestone, m3, after c2, 0d
    section AI service
    Matching API and baseline         :d1, 2026-12-21, 7d
    AI unit/integration tests (M4)     :milestone, m4, after d1, 0d
    section IoT and integration
    ESP8266 event prototype           :e1, 2026-12-30, 5d
    End-to-end integration (M5)        :e2, after e1, 5d
    section Verification and handoff
    System/privacy/usability tests     :f1, 2027-01-11, 5d
    Fixes, report, demo rehearsal      :f2, after f1, 5d
    Final submission (M6)              :milestone, m6, after f2, 0d
```

> Dates are illustrative and use a proposed start of 2 November 2026. Shift the chart to match the actual course deadline. Work may overlap if the developer has capacity.

## Milestones and acceptance criteria

| Milestone | Target | Acceptance criteria | Deliverables |
|---|---|---|---|
| M1 Requirements baseline | End of Week 2 | Scope, stakeholder needs, requirements, and risks documented | Proposal/SRS |
| M2 Design baseline | End of Week 4 | All six required diagrams and API/data design available | Design package |
| M3 UI + schema | End of Week 6 | Java Swing prototype runs; SQL schema reviewed | Java UI, SQL |
| M4 AI matching demo | End of Week 8 | Python endpoint ranks candidates and passes baseline tests | Flask service, tests |
| M5 IoT integration | End of Week 10 | Intake event is accepted or simulated end-to-end; duplicates handled by design | Firmware, event spec |
| M6 Final verification | End of Week 12 | Test evidence, evaluation, limitations, final proposal/report, and demo ready | Final submission |

## Work breakdown structure

1. Project initiation and feasibility
2. Requirements elicitation and specification
3. Architecture and data design
4. UML/DFD/ER modeling
5. Java interface and domain layer
6. MySQL schema
7. Python AI matching endpoint
8. ESP8266 intake-event firmware
9. Integration and test
10. Privacy/security review
11. Evaluation and documentation
12. Demonstration and submission

## Progress tracking

Use GitHub Issues or a project board with columns: Backlog, Ready, In Progress, Review/Test, Done. Each issue should include acceptance criteria and link to the relevant deliverable. Commit small, meaningful changes; tag milestones when the corresponding acceptance criteria are met.
