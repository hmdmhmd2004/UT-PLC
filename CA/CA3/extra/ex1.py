import os

# The exact files we need to build the NameAnalyzer pass correctly
files_to_extract = [
    # The core type classes
    "src/main/ast/types/Type.java",
    "src/main/ast/types/PrimitiveType.java",
    "src/main/ast/types/UserDefinedType.java",
    
    # Let's see if the template provided a starting point for the Type Checker
    "src/main/visitor/TypeChecker.java",
    "src/main/visitor/ExpressionTypeChecker.java" 
]

output_filename = "gemini_context.txt"

def build_context_file():
    print(f"Extracting files into {output_filename}...")
    
    with open(output_filename, 'w', encoding='utf-8') as outfile:
        for filepath in files_to_extract:
            # Handle OS-specific path separators safely
            safe_path = os.path.normpath(filepath)
            
            if os.path.exists(safe_path):
                try:
                    with open(safe_path, 'r', encoding='utf-8') as infile:
                        content = infile.read()
                        
                        # Add a clear delimiter so it's easy to read
                        outfile.write(f"========== {filepath} ==========\n")
                        outfile.write(content)
                        outfile.write("\n\n")
                        
                        print(f"  [SUCCESS] Added {filepath}")
                except Exception as e:
                    print(f"  [ERROR] Could not read {filepath}: {e}")
            else:
                print(f"  [WARNING] File not found: {filepath}")
                outfile.write(f"========== {filepath} ==========\n")
                outfile.write("// Error: File not found in directory.\n\n")
                
    print(f"\nDone! You can now upload '{output_filename}' or copy/paste its contents.")

if __name__ == "__main__":
    build_context_file()