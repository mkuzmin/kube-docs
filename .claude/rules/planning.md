# Planning Approach

## Go Slow, Think First

1. **Architecture before implementation** - Agree on high-level approach before discussing code details
2. **One problem at a time** - Don't jump ahead to future steps; focus on the immediate task
3. **Ask questions early** - Clarify scope, goals, and constraints before proposing solutions
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

## Implementation Steps

Structure steps as vertical slices (end-to-end through all layers), not horizontal layers.

```
# Bad - horizontal layers
1. Update all data classes
2. Update all extraction logic
3. Update all generation logic

# Good - vertical slices
1. Add field X (extract → generate → verify)
2. Add field Y (extract → generate → verify)
```

**Tracer bullets:** Start with the smallest possible end-to-end slice. Get it working, seek feedback, then expand. This validates the approach before investing in full implementation.

```
# Example: adding a new field to output
1. Pick ONE type, ONE field
2. Extract → generate → verify output looks right
3. Expand to all types
```
