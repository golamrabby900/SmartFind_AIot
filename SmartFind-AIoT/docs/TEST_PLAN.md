# SmartFind AIoT — Starter Test Plan

## Preconditions
- Use fictional data only.
- Run the Java interface and Python service locally.
- Use a development database; do not use real student personal information.
- For hardware testing, use a private test Wi-Fi and a local endpoint.

## Test cases

| ID | Area | Test | Expected result |
|---|---|---|---|
| TC-01 | Java UI | Submit a valid LOST report | Report appears in the list |
| TC-02 | Java validation | Submit blank description | Validation message; no report created |
| TC-03 | Matching | Add opposite-type report with overlapping terms | Candidate ranked with score above zero |
| TC-04 | Matching | Candidate is same report type | Excluded from Java prototype matching |
| TC-05 | AI API | POST valid query and candidates | HTTP 200 and descending score list |
| TC-06 | AI API | POST empty query | HTTP 400 with validation error |
| TC-07 | Database | Run schema in fresh development database | All tables and constraints created |
| TC-08 | IoT | Send valid event to configured endpoint | Server acknowledges and logs event |
| TC-09 | IoT | Send same event ID twice | Second request does not create a duplicate |
| TC-10 | Authorization | Non-staff attempts claim verification | Request rejected |
| TC-11 | Privacy | Open public report view | Contact and private evidence fields are not exposed |
| TC-12 | Handover | Attempt handover without verified claim | Handover blocked |

## Evaluation record template
- Dataset size and source:
- Number of manually labelled true matches:
- Precision@3:
- Median and 95th percentile response time:
- Valid intake events acknowledged:
- Duplicate events observed:
- Usability tasks completed / attempted:
- Limitations and false-positive examples:

The current repository implements only a subset of these tests. Mark each test as Pass, Fail, Blocked, or Not Implemented after running it.
