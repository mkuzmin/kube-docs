1. user provides you an argument - directory to process
2. for each file in the directory launch a Task subagent (general-purpose, model: sonnet). 
2. Process tasks sequentially (not in parallel). Stop for user review if issues need manual attention.

## subagent Instructions

**CRITICAL: Subagents must ONLY use Reand and Write tools. Do NOT create scripts, loops, or any other bash commands.**

Remove redundant names and filler verbs at the **start** of descriptions. Only remove text, never add or rewrite words elsewhere.

Filler verbs to remove: represents, specifies, contains, describes, holds, is.

- `_APIService.yaml`: `APIService represents a server for...` → `A server for...`
- `_ExpressionWarning.yaml`: `ExpressionWarning is a warning...` → `A warning...`
- `spec.yaml`: `Spec specifies information for locating...` → `Information for locating...`
- `status.yaml`: `Status contains derived information...` → `Derived information...`
- `_ServiceReference.yaml`: `ServiceReference holds a reference...` → `A reference...`
- `_APIServiceCondition.yaml`: `APIServiceCondition describes a condition...` → `A condition...`
- `ValidatingAdmissionPolicy/status.yaml`: `The status of the ValidatingAdmissionPolicy` → `The status`
- `ObjectSelector decides whether to run the webhook` → `Whether to run the webhook`

**Do NOT rewrite sentences.** If original says "We'd recommend X", keep it - don't change to "Recommended: X". 
