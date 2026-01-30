#!/usr/bin/env python3
"""Extract single-line formatted descriptions from YAML metadata files."""

import os
import sys
from pathlib import Path

import yaml


def extract_descriptions(types_dir: Path, output_dir: Path):
    """Process all API groups and extract single-line descriptions."""

    output_dir.mkdir(exist_ok=True)

    # Get all API groups (subdirectories)
    api_groups = sorted([d for d in types_dir.iterdir() if d.is_dir()])

    for group_dir in api_groups:
        group_name = group_dir.name
        output_file = output_dir / f"{group_name}.txt"

        # Find all YAML files in this group
        yaml_files = sorted(group_dir.rglob("*.yaml"))
        count = 0

        with open(output_file, 'w') as out:
            for yaml_file in yaml_files:
                try:
                    with open(yaml_file, 'r') as f:
                        data = yaml.safe_load(f)

                    if not data:
                        continue

                    # Extract .description.formatted
                    description = data.get('description', {})
                    if not isinstance(description, dict):
                        continue

                    formatted = description.get('formatted', '')
                    if not formatted:
                        continue

                    # Filter out multi-line values
                    if '\n' in formatted:
                        continue

                    # Get relative path from group_dir
                    rel_path = yaml_file.relative_to(group_dir)

                    # Write: filename, value, separator
                    out.write(f"{group_name}/{rel_path}\n")
                    out.write(f"{formatted}\n")
                    out.write("===\n")
                    count += 1

                except Exception as e:
                    print(f"Error processing {yaml_file}: {e}", file=sys.stderr)

        print(f"{group_name}: {count} descriptions")


def main():
    types_dir = Path("data/types")
    output_dir = Path("data/types")

    if not types_dir.exists():
        print(f"Error: {types_dir} does not exist", file=sys.stderr)
        sys.exit(1)

    extract_descriptions(types_dir, output_dir)
    print(f"Done. Output written to {output_dir}/")


if __name__ == "__main__":
    main()
