Implementing a branching strategy GitFlow
Branches:
- main - contains the latest stable release
  - Branches from: none
  - Merges into: none
  - Tags: v1.0.0 - v[X.Y.Z], X - major, Y - minor, Z - patch
  - Naming: none
- develop - uses for development. Branched from main as a strarting point
  - Branches from: main
  - Merges into: none
  - Naming: none
- feature - contains a new feature that is currently being developed.
    - Branches from: develop
    - Merges into: develop 
    - Naming: feature/JIRA-XXXX-feature-name
- release - contains a new release
    - Branches from: develop
    - Merges into: main, develop
    - Naming: release/v[X.Y.Z],
- hotfix - contains a hotfix. Fix for the bug in production.
    - Branches from: main
    - Merges into: main, develop
    - Naming: hotfix/JIRA-XXXX-hotfix-name

Github workflows:
- Brnach naming convention ?
- Branch protection rules ?
- Environment setup and branches matching ?
  - main
    - not allowed to merge from git
    - merge release and hotifx manually approved by release manager as a github action
  - hotfix = production, development?
    - not allowed to merge from git
    - merge release and hotifx manually approved by release manager as a github action
  - develop, release, feature = development

1. workflow - create release: workflow_dispatch, assign a release version (tag?) where to store the latest release
2. 