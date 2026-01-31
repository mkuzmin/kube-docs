# Planning Approach

## Go Slow, Think First

1. **Architecture before implementation** - Agree on high-level approach before discussing code details
2. **One problem at a time** - Don't jump ahead to future steps; focus on the immediate task
3. **Ask questions early** - Clarify scope, goals, and constraints before proposing solutions. Ask one question at a time.
4. **Verify claims** - Double-check numbers, counts, and assumptions with actual data

## Incremental Planning

- Don't deliver a "final plan" upfront
- Build understanding through conversation
- Use task lists for multi-step work
- Each step should be small and verifiable
- Follow-up refinements are normal iteration, not planning failures
- Don't discuss Step 2 while planning Step 1

## Plan Structure

A good plan answers:
- **What** is the goal?
- **What** is the current state?
- **What** is the target state?
- **How** to verify success?

No code in plans. Implementation details come after the approach is agreed upon.

## Slices

A plan consists of **slices** (subfeatures) - minimal user-facing changes. Slice features as thin as possible while satisfying two constraints:

1. **User-facing value** - the slice delivers visible progress
2. **Testable in isolation** - you can verify it works independently

**Tracer bullets** (from *The Pragmatic Programmer*): Start with the smallest possible slice. Get it working, seek feedback, then expand.

```
# Bad - horizontal layers (no user value until all complete)
1. Design all database schemas
2. Implement all backend endpoints
3. Build all frontend components

# Bad - too thin (can't test independently)
1. Create User model
2. Add validation method
3. Write unit test

# Good - vertical slices (each delivers testable user value)
1. User sees empty task list (visit page, see placeholder)
2. User sees tasks from database (add data, refresh, see items)
3. User can add a task (click button, fill form, see new item)
```

## Implementing a Slice

Each slice is built through progressive steps - start from the user-facing layer and go deeper, replacing stubs with real implementations one at a time.

```
# Example: "user can add a task" slice
1. Create task-adding form (verify layout)
2. Form sends HTTP request (verify request)
3. Create backend endpoint returning mocked response (verify round-trip)
4. Backend reads from database (verify schema)
5. Backend saves to database (verify persistence)
```

At each step: working software, testable, progressing toward the goal.
