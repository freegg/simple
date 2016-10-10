package com.you.bean;

import java.util.List;

public class User {
    private Integer id;

    private String name;

    private String password;

    private Integer age;

    private String mon;
    
    private Money money2;
    
    public Money getMoney2() {
		return money2;
	}

	public void setMoney2(Money money2) {
		this.money2 = money2;
	}

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	private List<Money> money;
    
    public List<Money> getMoney() {
		return money;
	}

	public void setMoney(List<Money> money) {
		this.money = money;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", age=" + age + "]";
	}
    
    
}