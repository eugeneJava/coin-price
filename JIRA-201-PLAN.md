# JIRA-201 Technical Architecture Plan: Add Calculator Support for atan, min, max

## 1. Executive Summary

This document outlines the technical design and implementation strategy for JIRA-201, which adds three new mathematical functions to the Calculator class: `atan` (arctangent), `min` (minimum of two numbers), and `max` (maximum of two numbers). Each function will be fully tested with comprehensive unit tests.

**Scope:** Core Calculator class enhancements with full test coverage  
**Effort:** Low to Medium (3 new methods + comprehensive unit tests)  
**Risk Level:** Low (no breaking changes, pure additions)

---

## 2. Requirements Analysis

### Feature Requirements
From JIRA-201:
- Add `atan()` method - inverse tangent function
- Add `min()` method - returns minimum of two values
- Add `max()` method - returns maximum of two values
- Include comprehensive unit tests for each function

### Functional Requirements
| Function | Signature | Input Type(s) | Return Type | Math Basis |
|----------|-----------|----------------|-------------|-----------|
| `atan` | `atan(double a)` | double | double | Math.atan() - inverse tangent in radians |
| `min` | `min(int a, int b)` | int, int | int | Minimum of two integers |
| `min` | `min(double a, double b)` | double, double | double | Minimum of two doubles |
| `max` | `max(int a, int b)` | int, int | int | Maximum of two integers |
| `max` | `max(double a, double b)` | double, double | double | Maximum of two doubles |

### Non-Functional Requirements
- Methods must follow existing code patterns in the Calculator class
- All methods must be public and non-static
- Return types must be consistent with related operations
- No external dependencies required (use Java built-in Math library)
- 100% method coverage with unit tests
- Tests must follow existing JUnit 5 test patterns

---

## 3. Current Codebase Analysis

### Existing Calculator Class Structure
**File:** `/src/main/java/org/example/Calculator.java`

Current methods follow established patterns:
- **Trigonometric functions:** `sin()`, `cos()`, `tanh()` - all accept double, return double
- **Arithmetic operations:** `add()`, `subtract()`, `multiply()` - overloaded for int types
- **Mathematical functions:** `pow()`, `abs()` - demonstrate mixed int/double handling
- **Pattern:** Simple wrapper methods around Java's `Math` class functions

### Existing Test Structure
**File:** `/src/test/java/org/example/CalculatorTest.java`

Testing patterns:
- JUnit 5 (Jupiter) with `@Test` annotations
- `assertEquals()` for assertions
- Floating-point comparisons use delta tolerance (e.g., `1e-9`)
- Multiple test cases per method covering edge cases
- Instance-based testing (creates Calculator instance in class)

### Key Observations
1. **No method overloading yet** - int and double variants could use overloading strategy
2. **Floating-point precision handling** - existing tests show proper tolerance usage for trigonometric functions
3. **Simple implementation philosophy** - direct wrapper methods are preferred
4. **Static imports** - AssertEquals is statically imported in tests

---

## 4. Technical Design

### 4.1 Method Signatures and Implementation Strategy

#### Method 1: `atan(double a)`
```java
/**
 * Returns the arctangent (inverse tangent) of a value in radians.
 * @param a the value for which to calculate arctangent
 * @return the arctangent of the input in radians, in the range [-π/2, π/2]
 */
public double atan(double a) {
    return Math.atan(a);
}
```
- **Rationale:** Follows trigonometric function pattern (sin, cos, tanh)
- **Java Support:** Math.atan() is native Java method
- **Return Range:** [-π/2, π/2] radians
- **Common Use Cases:** Angle calculations, coordinate transformations

#### Method 2: `min()` - Overloaded for int and double
```java
/**
 * Returns the minimum of two integers.
 * @param a first value
 * @param b second value
 * @return the smaller of a and b
 */
public int min(int a, int b) {
    return Math.min(a, b);
}

/**
 * Returns the minimum of two doubles.
 * @param a first value
 * @param b second value
 * @return the smaller of a and b
 */
public double min(double a, double b) {
    return Math.min(a, b);
}
```
- **Rationale:** Provides utility for finding minimum values; overloading allows both int and double
- **Java Support:** Math.min() is overloaded natively
- **Consistency:** Mirrors existing multiply/add int methods
- **Special Cases:** Handles NaN and negative zero per IEEE 754

#### Method 3: `max()` - Overloaded for int and double
```java
/**
 * Returns the maximum of two integers.
 * @param a first value
 * @param b second value
 * @return the larger of a and b
 */
public int max(int a, int b) {
    return Math.max(a, b);
}

/**
 * Returns the maximum of two doubles.
 * @param a first value
 * @param b second value
 * @return the larger of a and b
 */
public double max(double a, double b) {
    return Math.max(a, b);
}
```
- **Rationale:** Complement to min; provides maximum value utility
- **Java Support:** Math.max() is overloaded natively
- **Consistency:** Mirrors min() implementation

### 4.2 Design Decisions

| Decision | Rationale | Alternatives Considered |
|----------|-----------|------------------------|
| Use `Math.atan()` wrapper | Maintains consistency with sin/cos/tanh pattern | Implement custom arctangent algorithm (rejected: unnecessary complexity) |
| Overload min/max for int and double | Allows flexibility for different numeric types | Single method with Object/Number (rejected: less type-safe) |
| Return types match input domain | min(int, int) → int; min(double, double) → double | Always return double (rejected: loses type information) |
| No parameter validation | Input validation is implicit via Java's Math library | Add null checks (rejected: parameters are primitives, can't be null) |
| Public non-static methods | Matches existing Calculator class pattern | Static utility methods (rejected: inconsistent with class design) |

---

## 5. Implementation Strategy

### 5.1 Code Changes

**Primary File:** `src/main/java/org/example/Calculator.java`

**Additions:**
- Add `atan(double a)` method - 3 lines of code (method signature + return statement + blank line)
- Add `min(int a, int b)` method - 3 lines of code
- Add `min(double a, double b)` method - 3 lines of code
- Add `max(int a, int b)` method - 3 lines of code
- Add `max(double a, double b)` method - 3 lines of code

**Total:** ~15 lines of functional code (excluding comments)

**Insertion Points:**
- `atan()` - after `cos()` method (groups trigonometric functions)
- `min(int, int)` and `min(double, double)` - after arithmetic operations
- `max(int, int)` and `max(double, double)` - after min() methods

### 5.2 Test Implementation

**Primary File:** `src/test/java/org/example/CalculatorTest.java`

**New Test Methods to Add:**

1. **`testAtan()`** - Tests the atan function
   - Test case: `atan(0)` should equal `0.0`
   - Test case: `atan(1)` should equal `π/4` (≈0.7854)
   - Test case: `atan(-1)` should equal `-π/4` (≈-0.7854)
   - Test case: `atan(Double.POSITIVE_INFINITY)` should equal `π/2`
   - Test case: `atan(Double.NEGATIVE_INFINITY)` should equal `-π/2`

2. **`testMinInt()`** - Tests min with integers
   - Test case: `min(2, 3)` should equal `2`
   - Test case: `min(3, 2)` should equal `2`
   - Test case: `min(-1, -5)` should equal `-5`
   - Test case: `min(0, 1)` should equal `0`

3. **`testMinDouble()`** - Tests min with doubles
   - Test case: `min(2.5, 3.5)` should equal `2.5`
   - Test case: `min(3.7, 2.1)` should equal `2.1`
   - Test case: `min(-1.5, -5.5)` should equal `-5.5`
   - Test case: `min(Double.NaN, 1.0)` should return NaN

4. **`testMaxInt()`** - Tests max with integers
   - Test case: `max(2, 3)` should equal `3`
   - Test case: `max(3, 2)` should equal `3`
   - Test case: `max(-1, -5)` should equal `-1`
   - Test case: `max(0, -1)` should equal `0`

5. **`testMaxDouble()`** - Tests max with doubles
   - Test case: `max(2.5, 3.5)` should equal `3.5`
   - Test case: `max(3.7, 2.1)` should equal `3.7`
   - Test case: `max(-1.5, -5.5)` should equal `-1.5`
   - Test case: `max(Double.NaN, 1.0)` should return NaN

**Test Patterns:**
- Use `assertEquals()` with double delta (`1e-9`) for floating-point assertions
- Cover positive, negative, and zero inputs
- Test boundary conditions (infinity, NaN)
- Follow existing test naming conventions

**Total:** ~60 lines of test code

---

## 6. Architectural Impact Analysis

### 6.1 Scope of Changes
- **Confined to:** Calculator class and its test file
- **Public API additions:** 5 new public methods
- **Breaking changes:** None (purely additive)
- **Dependency changes:** None (uses existing Java Math library)

### 6.2 Backward Compatibility
- ✅ **Fully backward compatible** - No existing method signatures modified
- ✅ **No configuration changes required** - No build or dependency updates needed
- ✅ **No migration needed** - Existing code using Calculator class unaffected

### 6.3 Integration Points
- Calculator class is simple utility with no dependencies on other classes
- Methods use only Java standard library (Math class)
- No database, API, or external service interactions
- Easy to integrate into existing calculator applications

---

## 7. Testing Strategy

### 7.1 Test Coverage Goals
- **Method Coverage:** 100% (all 5 new methods)
- **Branch Coverage:** 100% (no conditional logic in implementations)
- **Test Case Distribution:**
  - `atan()`: 5 test cases (0, positive, negative, infinity)
  - `min(int, int)`: 4 test cases (normal, reversed, negatives, zero)
  - `min(double, double)`: 4 test cases (normal, reversed, negatives, NaN)
  - `max(int, int)`: 4 test cases (normal, reversed, negatives, zero)
  - `max(double, double)`: 4 test cases (normal, reversed, negatives, NaN)
  - **Total:** 21+ test cases

### 7.2 Test Execution Plan
1. Run `mvn test` to verify all tests pass
2. Verify no regressions in existing CalculatorTest tests
3. Check test output for coverage (all methods covered)
4. Validate floating-point precision handling

### 7.3 Edge Cases Covered
| Function | Edge Cases |
|----------|-----------|
| `atan()` | Zero, positive infinity, negative infinity, large values |
| `min()` | Equal values, zero, negative numbers, NaN (double) |
| `max()` | Equal values, zero, negative numbers, NaN (double) |

---

## 8. Implementation Checklist

### Phase 1: Implementation (Coding)
- [ ] Add `atan(double a)` method to Calculator class
- [ ] Add `min(int a, int b)` method to Calculator class
- [ ] Add `min(double a, double b)` method to Calculator class
- [ ] Add `max(int a, int b)` method to Calculator class
- [ ] Add `max(double a, double b)` method to Calculator class
- [ ] Verify code formatting and style consistency

### Phase 2: Testing (Unit Tests)
- [ ] Add `testAtan()` test method
- [ ] Add `testMinInt()` test method
- [ ] Add `testMinDouble()` test method
- [ ] Add `testMaxInt()` test method
- [ ] Add `testMaxDouble()` test method
- [ ] Run `mvn test` and verify all tests pass
- [ ] Verify no regressions in existing tests

### Phase 3: Quality Assurance
- [ ] Code review by team members
- [ ] Verify test coverage is 100% for new methods
- [ ] Manual verification of method behavior with various inputs
- [ ] Documentation review

### Phase 4: Integration & Deployment
- [ ] Merge feature branch into main
- [ ] Verify build succeeds: `mvn clean install`
- [ ] Tag release if applicable
- [ ] Update project documentation if needed

---

## 9. File Structure and Changes Summary

### Modified Files

**File 1: `src/main/java/org/example/Calculator.java`**
- **Type:** Enhancement
- **Change Type:** Addition of 5 new public methods
- **Lines Added:** ~20 (including comments and formatting)
- **Compatibility:** Backward compatible
- **Location:** Insert after existing trigonometric functions and arithmetic operations

**File 2: `src/test/java/org/example/CalculatorTest.java`**
- **Type:** Test Enhancement
- **Change Type:** Addition of 5 new test methods
- **Lines Added:** ~60 (including assertions and comments)
- **Coverage Impact:** Increases test coverage to 100% for new methods
- **Location:** Append new test methods after existing tests

---

## 10. Risk Assessment and Mitigation

### Identified Risks

| Risk | Likelihood | Impact | Mitigation |
|------|-----------|--------|-----------|
| Floating-point precision issues in atan() | Low | Low | Use 1e-9 delta in tests (proven pattern) |
| NaN handling in min/max with doubles | Low | Low | Test NaN cases explicitly |
| Method overloading confusion | Very Low | Very Low | Clear documentation and consistent naming |
| Integration issues | Very Low | Very Low | Simple wrapper methods, no side effects |

### Risk Mitigation Strategies
1. **Comprehensive test coverage** - All edge cases tested before merge
2. **Code review process** - Peer review ensures quality
3. **Existing patterns** - Leverage proven patterns from existing code
4. **No breaking changes** - Backward compatibility guaranteed

---

## 11. Success Criteria

### Acceptance Criteria
- ✅ All 5 methods implemented in Calculator class
- ✅ All methods follow existing code patterns and style
- ✅ 100% test coverage for new methods (5 test methods with 21+ test cases)
- ✅ All tests pass: `mvn test` runs successfully
- ✅ No regressions in existing tests
- ✅ Code builds successfully: `mvn clean install`
- ✅ Code review approved by at least one team member
- ✅ PR merged to main branch

### Definition of Done
- Code implementation complete and tested
- Unit tests written and passing
- Code review completed and approved
- Documentation updated (JIRA-201-PLAN.md)
- PR merged and closed
- No open issues or TODOs related to JIRA-201

---

## 12. Implementation Notes

### Coding Standards
- Follow existing Calculator class conventions
- Use Java naming conventions (camelCase for methods)
- Maintain consistent indentation and formatting
- Add JavaDoc comments for all public methods

### Dependencies
- **No new dependencies** required
- Uses existing: Java 17+, JUnit 5, Maven
- No changes to pom.xml needed

### Build & Test Commands
```bash
# Compile project
mvn clean compile

# Run tests
mvn test

# Full build
mvn clean install
```

### Code Review Checklist for Reviewers
1. ✅ Methods implement correct mathematical operations
2. ✅ Code follows existing patterns in Calculator class
3. ✅ Method signatures are appropriate
4. ✅ Return types match function behavior
5. ✅ Test coverage is comprehensive
6. ✅ Edge cases are handled
7. ✅ No breaking changes introduced
8. ✅ Code is maintainable and readable
9. ✅ Documentation is clear and accurate
10. ✅ All tests pass

---

## 13. Timeline and Effort Estimate

| Task | Effort | Duration |
|------|--------|----------|
| Code Implementation (5 methods) | 30 min | 30 min |
| Unit Test Implementation (5 test methods) | 45 min | 45 min |
| Code Review & Refinement | 30 min | 30 min |
| Integration & Verification | 15 min | 15 min |
| **Total** | **2 hours** | **2 hours** |

---

## 14. Appendix: Reference Information

### Java Math Methods Reference
- `Math.atan(double)` - Returns arctangent in radians [-π/2, π/2]
- `Math.min(int, int)` - Returns minimum of two integers
- `Math.min(double, double)` - Returns minimum of two doubles
- `Math.max(int, int)` - Returns maximum of two integers
- `Math.max(double, double)` - Returns maximum of two doubles

### Related Trigonometric Functions
- `atan2(y, x)` - Two-argument arctangent (not in scope for JIRA-201)
- `atan(x)` - Single-argument arctangent (in scope)

### Trigonometric Function Ranges
- `sin, cos`: domain ℝ, range [-1, 1]
- `atan`: domain ℝ, range [-π/2, π/2]
- `tanh`: domain ℝ, range (-1, 1)

---

**Document Version:** 1.0  
**Date Created:** 2026-06-27  
**Status:** Ready for Review  
**JIRA Ticket:** JIRA-201  
**Feature:** Add Calculator Support for atan, min, max
