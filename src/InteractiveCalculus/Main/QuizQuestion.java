package InteractiveCalculus.Main;

public class QuizQuestion {
	
	int id;
	String question;
	String a1;
	String a2;
	String a3;
	String a4;
	int correct;
	int section;
	
	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public QuizQuestion(int id, String question, String a1, String a2,
			String a3, String a4, int correct) {
		super();
		this.id = id;
		this.question = question;
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.correct = correct;
	}
	
	public QuizQuestion(int id, String question, String a1, String a2,
			String a3, String a4, int correct, int section) {
		super();
		this.id = id;
		this.question = question;
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.correct = correct;
		this.section = section;
	}
	
	public QuizQuestion() {
		super();
	}
	
	public int getId() {
		return id;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getA1() {
		return a1;
	}
	public void setA1(String a1) {
		this.a1 = a1;
	}
	public String getA2() {
		return a2;
	}
	public void setA2(String a2) {
		this.a2 = a2;
	}
	public String getA3() {
		return a3;
	}
	public void setA3(String a3) {
		this.a3 = a3;
	}
	public String getA4() {
		return a4;
	}
	public void setA4(String a4) {
		this.a4 = a4;
	}
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	
	public String answerChoice(int num) {
		if (num == 1)
			return this.getA1();
		else if (num == 2)
			return this.getA2();
		else if (num == 3)
			return this.getA3();
		else if (num == 4)
			return this.getA4();
		else 
			return "";
	}
	
}
