package fga.mds.gpp.dojotestapplication.Model;

import fga.mds.gpp.dojotestapplication.Exception.UserException;

public class User {

    private String name;
    private Integer age;

    public User(String name, String age) throws UserException{
        setName(name);
        setAge(age);
    }

    private void setName(String name) throws UserException {
        if (name != null && !name.isEmpty()) {
            Integer MAX_NAME_LENGTH = 50;

            if (name.length() <= MAX_NAME_LENGTH) {
                this.name = name;
            } else  {
                throw new UserException("Digite um nome de até 50 caracteres");
            }
        } else {
            throw new UserException("O nome não pode ser vazio");
        }
    }

    private void setAge (String age) throws UserException {
        try{
            this.age = Integer.valueOf(age);
        }catch(NumberFormatException userException){
            throw new UserException("A idade não pode ser vazia");
        }
    }

    public String getName(){
        return name;
    }

    public Integer getAge() {
        return age;
    }

}
