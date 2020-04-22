package com.example.ht1;


public abstract class Account {
        String acc_num;
        float balance;
        int user_id;
        String open_date;
        float pay_lim;

        public Account(String a, float b, int c, String d, float e) {
            acc_num = a;
            balance = b;
            user_id = c;
            open_date = d;
            pay_lim = e;
        }
        //methods of class Account->

        public float getBalance() {
            return balance;
        }
        public void pay(float sum) {
            balance = balance-sum;
        }
        public void addMoney(float sum) {
            balance = balance+sum;
        }
        public String getAccountNumber() {
            return acc_num;
        }
        public void setPayLim(float pl) {
            pay_lim = pl;
        }
        public float getPayLim() {
            return pay_lim;
        }
        public String getOpenDate() {
            return open_date;
        }
        public int getUserID() {
            return user_id;
        }
    }

    class Debit_account extends Account {

        public Debit_account(String a, float b, int c, String d, float e) {
            super(a,b,c,d,e);
        }
    }

    class Credit_account extends Account {

        float credit;

        public Credit_account(String a, float b, int c, String d, float e, float f) {
            super(a,b,c,d,e);
            credit = f;
        }
        //methods of class Credit account->

        public float getCredit(float cr) {
            return credit;
        }
    }

    class Debit_card extends Debit_account {

        float use_lim;
        float draw_lim;
        String area;
        String card_num;

        public Debit_card (String a, float b, int c, String d, float e, float g, float h, String i, String j) {
            super(a,b,c,d,e);
            use_lim = g;
            draw_lim = h;
            area = i;
            card_num = j;
        }

        //methods of class debit card->

        public void setArea(String ar) {
            area = ar;
        }

        public String getArea() {
            return area;
        }

        public void setUseLimit(float ul) {
            use_lim = ul;
        }

        public float getUseLimit() {
            return use_lim;
        }

        public void setDrawLimit(float dl) {
            draw_lim = dl;
        }

        public float getDrawLimit() {
            return draw_lim;
        }

        public String getCardNum() {
            return card_num;
        }

    }

    class Credit_card extends Credit_account {

        float use_lim;
        float draw_lim;
        String area;
        String card_num;

        public Credit_card (String a, float b, int c, String d, float e, float f, float g, float h, String i, String j) {
            super(a,b,c,d,e,f);
            use_lim = g;
            draw_lim = h;
            area = i;
            card_num = j;
        }

        //methods of class credit card->

        public void setArea(String ar) {
            area = ar;
        }

        public String getArea() {
            return area;
        }

        public void setUseLimit(float ul) {
            use_lim = ul;
        }

        public float getUseLimit() {
            return use_lim;
        }

        public void setDrawLimit(float dl) {
            draw_lim = dl;
        }

        public float getDrawLimit() {
            return draw_lim;
        }

        public String getCardNum() {
            return card_num;
        }

    }

