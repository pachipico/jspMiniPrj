import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {


	public static void main(String[] args) {
		
		Pattern pattern = Pattern.compile("(#[A-Za-z0-9-_]+)(?:#[A-Za-z0-9-_]+)*\\b");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()){
		    System.out.println(matcher.group(1)); 
		} 
	}
}
