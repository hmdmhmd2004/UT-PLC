from pathlib import Path

def get_tree(directory, prefix=""):
    lines = []

    entries = sorted(
        directory.iterdir(),
        key=lambda p: (p.is_file(), p.name.lower())
    )

    for i, entry in enumerate(entries):
        is_last = i == len(entries) - 1

        connector = "└── " if is_last else "├── "
        lines.append(prefix + connector + entry.name)

        if entry.is_dir():
            extension = "    " if is_last else "│   "
            lines.extend(get_tree(entry, prefix + extension))

    return lines


if __name__ == "__main__":
    root_dir = Path(__file__).parent

    tree_text = "\n".join(get_tree(root_dir))

    output_file = root_dir / "tree.txt"
    output_file.write_text(tree_text, encoding="utf-8")

    print(f"Tree saved to: {output_file}")