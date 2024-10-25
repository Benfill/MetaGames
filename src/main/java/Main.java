import controller.App;
import util.SpringContextUtil;

public class Main {
	public static void main(String[] args) {
		App app = (App) SpringContextUtil.getBean("app");
	}
}
