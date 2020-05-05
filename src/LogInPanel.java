import dao.UserDao;
import dto.User;
import exception.ReturnException;

public class LogInPanel {
    private UserDao userDao = UserDao.getInstance();

    public void logeIn() throws Exception {
        System.out.println("********welcome to log in panel!********");
        System.out.println("----enter \"exit\" any time you want to exit----");
        String userName = getInputString("enter your user name");
        String password = getInputString("enter your password");
        User user = userDao.search(userName);
        if (user == null) throw new Exception("the user name is not correct");
        if (!user.getPassword().equals(password)) throw new Exception("wrong password");
        GetOrderPanel.getOrder(userName);
    }

    private String getInputString(String message) throws ReturnException {
        String input;
        do {
            System.out.println(message);
            input = StoreManager.scanner.nextLine();
            if (input.equals("exit")) throw new ReturnException("good bye");
        } while (input.length() == 0);
        return input.trim();
    }
}
