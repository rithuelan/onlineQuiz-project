import java.util.*;

class Question {
    private String question, optionA, optionB, optionC, optionD, correctAnswer;

    public Question(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() { return question; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public String getOptionD() { return optionD; }
    public String getCorrectAnswer() { return correctAnswer; }
}

public class Main {
    private static final Map<String, Map<String, List<Question>>> quizData = new HashMap<>();
    private static int score = 0;
    private static String username, languagePreference, difficultyPreference;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome User
        System.out.println("🎉 Welcome to the Online Quiz Competition! 🎉");
        System.out.print("Enter your name: ");
        username = scanner.nextLine();

        // Language Preference
        System.out.print("Choose a programming language (C, Python, Java, C++, JS): ");
        languagePreference = scanner.nextLine().toLowerCase();

        // Difficulty Level Preference
        System.out.print("Select difficulty level (Easy, Medium, Hard): ");
        difficultyPreference = scanner.nextLine().toLowerCase();

        System.out.println("\n🚀 Get ready, " + username + "! Your " + difficultyPreference + " level quiz for " + languagePreference + " starts now.\n");

        initializeQuestions();
        startQuiz(languagePreference, difficultyPreference);
        displayResult();
    }

    private static void initializeQuestions() {
        quizData.put("java", new HashMap<>());
        quizData.put("python", new HashMap<>());
        quizData.put("c", new HashMap<>());
        quizData.put("c++", new HashMap<>());
        quizData.put("js", new HashMap<>());

        // Java Questions
        quizData.get("java").put("easy", Arrays.asList(
            new Question("What does JVM stand for?", "A. Java Virtual Machine", "B. Java Vector Model", "C. Java Variable Manager", "D. Java Version Module", "A"),
            new Question("Which keyword defines a constant variable?", "A. final", "B. static", "C. const", "D. define", "A"),
            new Question("Which loop always executes at least once?", "A. for", "B. while", "C. do-while", "D. switch", "C"),
            new Question("Which keyword is used for object creation?", "A. new", "B. create", "C. construct", "D. build", "A"),
            new Question("Which access modifier restricts visibility to only within a class?", "A. private", "B. public", "C. protected", "D. package-private", "A")
        ));
        
        quizData.get("java").put("medium", Arrays.asList(
            new Question("Which operator is used for object comparison?", "A. ==", "B. equals()", "C. compareTo()", "D. isEqual()", "B"),
            new Question("What is the default access modifier in Java?", "A. private", "B. public", "C. protected", "D. package-private", "D"),
            new Question("What is method overloading?", "A. Using multiple constructors", "B. Defining methods with the same name but different parameters", "C. Replacing an inherited method", "D. None of the above", "B"),
            new Question("Which feature in Java enables multiple methods to perform different tasks?", "A. Encapsulation", "B. Polymorphism", "C. Inheritance", "D. Abstraction", "B"),
            new Question("Which keyword prevents a method from being overridden?", "A. final", "B. static", "C. private", "D. protected", "A")
        ));
        
        quizData.get("java").put("hard", Arrays.asList(
            new Question("What is the time complexity of binary search?", "A. O(1)", "B. O(n)", "C. O(log n)", "D. O(n^2)", "C"),
            new Question("Which Java collection maintains insertion order?", "A. HashSet", "B. TreeSet", "C. LinkedHashSet", "D. PriorityQueue", "C"),
            new Question("Which class is used for file handling in Java?", "A. BufferedReader", "B. Scanner", "C. File", "D. Writer", "C"),
            new Question("Which method is used to start a thread in Java?", "A. begin()", "B. run()", "C. start()", "D. execute()", "C"),
            new Question("Which exception is thrown when dividing by zero in Java?", "A. ArithmeticException", "B. DivideByZeroException", "C. NullPointerException", "D. NumberFormatException", "A")
        ));

        
        // Python Questions
        quizData.get("python").put("easy", Arrays.asList(
            new Question("Which keyword is used to define a function in Python?", "A. def", "B. func", "C. function", "D. declare", "A"),
            new Question("Which data structure is immutable?", "A. List", "B. Dictionary", "C. Tuple", "D. Set", "C"),
            new Question("What symbol is used for comments in Python?", "A. #", "B. //", "C. --", "D. %%", "A"),
            new Question("Which function prints output in Python?", "A. output()", "B. display()", "C. print()", "D. println()", "C"),
            new Question("Which function takes user input in Python?", "A. input()", "B. scan()", "C. read()", "D. capture()", "A")
        ));
        quizData.get("python").put("medium", Arrays.asList(
            new Question("What does PEP stand for in Python?", "A. Python Enterprise Project", "B. Python Enhancement Proposal", "C. Python Execution Process", "D. Python Evaluation Protocol", "B"),
            new Question("How do you create a virtual environment in Python?", "A. python -m venv env", "B. pip install env", "C. python create env", "D. py -new-env", "A"),
            new Question("Which Python function returns the length of a list?", "A. count()", "B. len()", "C. length()", "D. size()", "B"),
            new Question("Which Python module is used for multithreading?", "A. multiprocessing", "B. threading", "C. concurrent", "D. async", "B"),
            new Question("Which decorator is used for class methods?", "A. @staticmethod", "B. @classmethod", "C. @property", "D. @private", "B")
        ));
        quizData.get("python").put("hard", Arrays.asList(
            new Question("What is the time complexity of quicksort in the average case?", "A. O(n)", "B. O(log n)", "C. O(n log n)", "D. O(n^2)", "C"),
            new Question("Which Python function sorts a list?", "A. sort()", "B. sorted()", "C. arrange()", "D. order()", "B"),
            new Question("Which Python library is used for deep learning?", "A. TensorFlow", "B. Pandas", "C. OpenCV", "D. Flask", "A"),
            new Question("Which Python feature allows functions inside functions?", "A. Nested functions", "B. Recursion", "C. Lambda", "D. Closures", "A"),
            new Question("Which function converts a string into an integer?", "A. parseInt()", "B. int()", "C. convertInt()", "D. castInt()", "B")
        ));

        // C Questions
        quizData.get("c").put("easy", Arrays.asList(
            new Question("Which operator is used for address access in C?", "A. *", "B. &", "C. ->", "D. $", "B"),
            new Question("Which data type stores single characters?", "A. char", "B. string", "C. text", "D. letter", "A"),
            new Question("Which function reads user input?", "A. gets()", "B. scanf()", "C. read()", "D. input()", "B"),
            new Question("Which symbol ends a statement in C?", "A. .", "B. ;", "C. :", "D. ,", "B"),
            new Question("Which operator performs multiplication in C?", "A. *", "B. ×", "C. mul", "D. ^", "A")
        ));
        quizData.get("c").put("medium", Arrays.asList(
            new Question("Which function is used to allocate memory dynamically in C?", "A. malloc()", "B. alloc()", "C. memory()", "D. assign()", "A"),
            new Question("Which operator is used for logical AND in C?", "A. &&", "B. ||", "C. AND", "D. &", "A"),
            new Question("Which keyword is used to define a structure in C?", "A. struct", "B. class", "C. record", "D. object", "A"),
            new Question("Which function converts a string to an integer?", "A. atoi()", "B. intParse()", "C. toInt()", "D. castInt()", "A"),
            new Question("Which data structure follows FIFO in C?", "A. Stack", "B. Queue", "C. LinkedList", "D. Tree", "B")
        ));
        quizData.get("c").put("hard", Arrays.asList(
            new Question("What is the time complexity of merge sort?", "A. O(n)", "B. O(log n)", "C. O(n log n)", "D. O(n^2)", "C"),
            new Question("Which header file is required for using malloc()?", "A. stdlib.h", "B. string.h", "C. memalloc.h", "D. memory.h", "A"),
            new Question("Which C feature allows functions to call themselves?", "A. Recursion", "B. Loops", "C. Iteration", "D. Pointers", "A"),
            new Question("Which keyword is used for defining constants in C?", "A. #define", "B. const", "C. final", "D. static", "A"),
            new Question("Which sorting algorithm is best for nearly sorted arrays?", "A. Bubble Sort", "B. Insertion Sort", "C. Selection Sort", "D. QuickSort", "B")
        ));
        
        // JavaScript Questions
        quizData.get("js").put("easy", Arrays.asList(
            new Question("Which keyword declares a variable in JavaScript?", "A. var", "B. let", "C. const", "D. all of the above", "D"),
            new Question("Which function prints output in JavaScript?", "A. display()", "B. log()", "C. console.log()", "D. print()", "C"),
            new Question("How do you create an array in JavaScript?", "A. []", "B. {}", "C. ()", "D. <>", "A"),
            new Question("Which symbol denotes a single-line comment in JavaScript?", "A. //", "B. #", "C. --", "D. $$", "A"),
            new Question("What does 'typeof' return for an array?", "A. array", "B. object", "C. list", "D. undefined", "B")
        ));
        quizData.get("js").put("medium", Arrays.asList(
            new Question("Which event occurs when the user clicks on an element?", "A. click", "B. hover", "C. focus", "D. activate", "A"),
            new Question("How do you check if a variable is 'undefined' in JavaScript?", "A. typeof x === 'undefined'", "B. x == null", "C. x.empty", "D. x === false", "A"),
            new Question("What does JSON stand for?", "A. JavaScript Object Notation", "B. Java System Object Name", "C. Java Serialized Object Network", "D. JavaScript Ordered Nodes", "A"),
            new Question("Which method converts a string into an integer?", "A. parseInt()", "B. parseFloat()", "C. int()", "D. strToInt()", "A"),
            new Question("Which function is used to execute code after a delay?", "A. setTimeout()", "B. delay()", "C. wait()", "D. timeout()", "A")
        ));
        quizData.get("js").put("hard", Arrays.asList(
            new Question("Which JavaScript function is used to execute code at specific intervals?", "A. setTimeout()", "B. setInterval()", "C. timer()", "D. wait()", "B"),
            new Question("What is closure in JavaScript?", "A. A function inside another function", "B. A class method", "C. A hidden variable", "D. A function that remembers its scope", "D"),
            new Question("Which JavaScript feature allows asynchronous execution?", "A. Promises", "B. Callbacks", "C. Async/Await", "D. All of the above", "D"),
            new Question("Which keyword is used to declare a constant variable?", "A. const", "B. let", "C. var", "D. static", "A"),
            new Question("Which JavaScript framework is used for building interactive UI?", "A. Angular", "B. React", "C. Vue", "D. All of the above", "D")
        ));

        // C++ Questions
        quizData.get("c++").put("easy", Arrays.asList(
            new Question("Which keyword is used to define a class in C++?", "A. struct", "B. class", "C. object", "D. define", "B"),
            new Question("Which operator is used for pointer dereferencing?", "A. *", "B. &", "C. ->", "D. ^", "A"),
            new Question("Which function prints output in C++?", "A. printf()", "B. cout <<", "C. display()", "D. log()", "B"),
            new Question("Which header file is required for input/output operations?", "A. iostream", "B. stdlib.h", "C. math.h", "D. io.h", "A"),
            new Question("Which function reads user input in C++?", "A. cin", "B. scanf()", "C. gets()", "D. input()", "A")
        ));
        quizData.get("c++").put("medium", Arrays.asList(
            new Question("Which feature of C++ supports multiple functions with the same name?", "A. Overloading", "B. Overriding", "C. Inheritance", "D. Encapsulation", "A"),
            new Question("What is the default access modifier in C++?", "A. private", "B. public", "C. protected", "D. package-private", "A"),
            new Question("Which concept in C++ prevents accidental changes to variables?", "A. const", "B. final", "C. static", "D. immutable", "A"),
            new Question("Which keyword is used to define a template in C++?", "A. template", "B. generic", "C. class", "D. function", "A"),
            new Question("Which feature allows multiple inheritance in C++?", "A. Virtual functions", "B. Templates", "C. Abstract classes", "D. Interfaces", "A")
        ));
        quizData.get("c++").put("hard", Arrays.asList(
            new Question("Which sorting algorithm is best for nearly sorted arrays?", "A. Bubble Sort", "B. Insertion Sort", "C. Selection Sort", "D. QuickSort", "B"),
            new Question("Which data structure follows FIFO?", "A. Stack", "B. Queue", "C. LinkedList", "D. Tree", "B"),
            new Question("Which keyword is used for memory allocation?", "A. malloc", "B. new", "C. alloc", "D. create", "B"),
            new Question("Which C++ feature supports dynamic binding?", "A. Polymorphism", "B. Encapsulation", "C. Abstraction", "D. Inheritance", "A"),
            new Question("Which operator is used for object pointers?", "A. ->", "B. .", "C. *", "D. &", "A")
        ));

        
    }

    private static void startQuiz(String language, String difficulty) {
        Scanner scanner = new Scanner(System.in);
        List<Question> selectedQuestions = quizData.getOrDefault(language, new HashMap<>()).getOrDefault(difficulty, new ArrayList<>());

        if (selectedQuestions.isEmpty()) {
            System.out.println("🚨 No questions available for this selection. Please restart and choose another option.");
            return;
        }

        for (Question q : selectedQuestions) {
            System.out.println(q.getQuestion());
            System.out.println("A. " + q.getOptionA());
            System.out.println("B. " + q.getOptionB());
            System.out.println("C. " + q.getOptionC());
            System.out.println("D. " + q.getOptionD());

            System.out.print("Your Answer: ");
            String answer = scanner.nextLine().toUpperCase();

            if (answer.equals(q.getCorrectAnswer())) {
                score++;
                System.out.println("✅ Correct!\n");
            } else {
                System.out.println("❌ Wrong! Correct Answer: " + q.getCorrectAnswer() + "\n");
            }
        }
    }

    private static void displayResult() {
        double percentage = (score / 5.0) * 100;
        System.out.println("\n📊 Quiz Over! You got " + score + "/5 questions correct.");
        System.out.println("Your final score percentage: " + percentage + "%");

        if (score == 5) {
            System.out.println("🎊 Amazing! You got *all correct*! Congratulations! 🎊");
        } else if (score >= 3) {
            System.out.println("🔥 Well done! You're almost there. Keep practicing! 🔥");
        } else if (score >= 1) {
            System.out.println("👍 Keep going! Better luck next time! 👍");
        } else {
            System.out.println("😔 Bad luck today, but you'll improve! 😔");
        }

        System.out.println("\n🙏 Thank you for playing! See you next time! 🙏");
    }
}
