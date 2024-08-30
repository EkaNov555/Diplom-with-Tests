package datahelper;

public class DataForTests {
    public static class User {
        private String login;
        private String password;

        public User(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class Login {
        private String rusSymbols;
        private String specialSymbols;
        private String spaceSymbol;
        private String loginWithSpace;

        public Login(String rusSymbols, String specialSymbols, String spaceSymbol, String loginWithSpace) {
            this.rusSymbols = rusSymbols;
            this.specialSymbols = specialSymbols;
            this.spaceSymbol = spaceSymbol;
            this.loginWithSpace = loginWithSpace;
        }

        public String getRusSymbols() {
            return rusSymbols;
        }

        public String getSpecialSymbols() {
            return specialSymbols;
        }

        public String getSpaceSymbol() {
            return spaceSymbol;
        }

        public String getLoginWithSpace() {
            return loginWithSpace;
        }
    }

    public static class Password {
        private String specialSymbolsInPass;

        public Password(String specialSymbolsInPass) {
            this.specialSymbolsInPass = specialSymbolsInPass;
        }

        public String getSpecialSymbolsInPass() {
            return specialSymbolsInPass;
        }
    }

    public static class News {
        private String name;
        private String description;

        public News(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }

    public static User correctUser() {
        return new User("login2", "password2");
    }

    public static User inCorrectUser() {
        return new User("Hello", "Hello555");
    }

    public static User lessSymbolsThanNeed() {
        return new User("8a", "8a");
    }

    public static User moreSymbolsThanNeed() {
        return new User("4612786139798746786651743574137687897795379875987389579755",
                "4612786139798746786651743574137687897795379875987389579755");
    }

    public static Login badSymbolsLogin() {
        return new Login("0ыфолр", ";:?/><\"'|\\~!*+-={}[]@#$%", " ", "login login");
    }

    public static Password badSymbolsPassword() {
        return new Password(";:?/><\"'|\\~!*+-={}[]@#$%");
    }
}
