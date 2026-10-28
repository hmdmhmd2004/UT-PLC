import os

# The exact files needed to build the CodeGenerator pass correctly without assumptions
files_to_extract = [
    # The Visitor interface and base class to see exact visit() signatures
    "src/main/visitor/Visitor.java",
    "src/main/visitor/IVisitor.java",
    "src/main/visitor/CodeGenerator.java",
    
    # Declarations to check getters for methods, modules, and parameters
    "src/main/ast/declarations/MethodDecl.java",
    "src/main/ast/declarations/ModuleDecl.java",
    "src/main/ast/declarations/StructDecl.java",
    "src/main/ast/declarations/Parameter.java",
    
    # Statements to check how conditions and bodies are stored
    "src/main/ast/statements/IfStmt.java",
    "src/main/ast/statements/WhileStmt.java",
    "src/main/ast/statements/AssignStmt.java",
    
    # Expressions and Operators to check exact enum names (e.g., ADD, SUB)
    "src/main/ast/expressions/BinaryExpression.java",
    "src/main/ast/expressions/UnaryExpression.java",
    "src/main/ast/expressions/SimpleLoc.java",
    "src/main/ast/expressions/operators/BinaryOperator.java",
    "src/main/ast/expressions/operators/UnaryOperator.java"
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