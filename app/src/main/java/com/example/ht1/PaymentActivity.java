package com.example.ht1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.ht1.Main2Activity.userIdSelection;
import static com.example.ht1.Main2Activity.userNameSelection;

public class PaymentActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {
    TextView textViewHeaderPayment;
    TextView textViewWriteAccount;
    TextView textViewSum;
    TextView textViewDueDate;
    TextView textViewFreq;
    TextView textViewPay;
    TextView textViewName;
    EditText editTextAccountNumber;
    EditText editTextSum;
    EditText editTextName;
    EditText editTextFreq;
    Spinner spinner2;

    public static String selectedAccNum;

    int selection;
    int i = 0;
    int a = 0;
    int b = 0;
    int yearInt;
    int monthInt;
    int dayInt;
    float payLim;
    float bal;
    float credit_lim;
    String date_selection;
    String account_num = "";
    String sum_String;
    String year_;
    String month_;
    String day_;
    String time_date;
    float sum;
    int date_;

    String dayString;
    String monthString;
    String yearString;

    int id_orginal;

    String account;
    String name;
    String date_Freq_String;
    int date;
    int date_Freq_Int;
    String dateString;
    int dateInt;

    String freq_String;
    int freq_Int;

    ArrayList<AccountEvent> account_event = new ArrayList<>();
    ArrayList<Debit_account> debit_accounts = new ArrayList<>();
    ArrayList<Credit_account> credit_accounts = new ArrayList<>();
    ArrayList<PayLog> paylog = new ArrayList<>();
    List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the activity layout here
        View contentView = inflater.inflate(R.layout.activity_payment, null, false);
        mDrawer.addView(contentView, 0);

        textViewHeaderPayment = (TextView) findViewById(R.id.textViewHeaderPayment);
        textViewHeaderPayment.setText("Select your account and pay bills");
        textViewWriteAccount = (TextView) findViewById((R.id.textViewWriteAccount));
        textViewWriteAccount.setText("Write account number");
        textViewSum = (TextView) findViewById(R.id.textViewSum);
        textViewSum.setText("Write amount in €");
        textViewDueDate = (TextView) findViewById(R.id.textViewDueDate);
        textViewDueDate.setText("Write due date if you pay bill then");
        textViewFreq = (TextView) findViewById(R.id.textViewFreq);
        textViewFreq.setText("Do you want to make payment frequent?");
        textViewPay = (TextView) findViewById(R.id.textViewPay);
        textViewPay.setText("");
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewName.setText("Write receiver's name");

        editTextAccountNumber = (EditText) findViewById(R.id.editTextAccountNumber);
        editTextAccountNumber.setText("");
        editTextSum = (EditText) findViewById(R.id.editTextSum);
        editTextFreq = (EditText) findViewById(R.id.editTextFreq);
        //editTextDuedate = (EditText) findViewById(R.id.editTextDueDate);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText("");

        findViewById(R.id.buttonDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDailog();
            }
        });

        final Switch switchDueDate = (Switch) findViewById(R.id.switchDueDate);
        final Switch switchFreq = (Switch) findViewById(R.id.switchFreq);
        switchDueDate.setTextOn("On");
        switchDueDate.setTextOff("Off");
        switchFreq.setTextOn("On");
        switchFreq.setTextOff("Off");

        spinner();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2 = (Spinner) findViewById(R.id.spinnerPayment);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);

        Button button = (Button) findViewById(R.id.buttonPay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Activity here
                String status1, status2;
                if (switchDueDate.isChecked() && switchFreq.isChecked()) {
                    payDueFreq();
                } else if (switchFreq.isChecked()) {
                    payFreq();
                } else if (switchDueDate.isChecked()) {
                    payDue();
                } else {
                    payNormal();
                }
            }
        });
    }
    private void showDatePickerDailog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
              this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

                );
                datePickerDialog.show();
    }
    public void spinner() {
        int id = 0;
        System.out.println(userIdSelection);

        //get arrayslists from MainActivity
        account_event =  MainActivity.getInstance().getAccountEventlist();
        debit_accounts = MainActivity.getInstance().getDebitaccountlist();
        credit_accounts = MainActivity.getInstance().getCreditaccountlist();
        paylog =  MainActivity.getInstance().getPaylog();

        for (Debit_account d_a : debit_accounts) {
            id = userIdSelection;
            if (id == d_a.getUserID()) {
                list.add(d_a.getAccountNumber());
            }
        }
        for (Credit_account c_a : credit_accounts) {
            id = userIdSelection;
            if (id == c_a.getUserID()) {
                list.add(c_a.getAccountNumber());
            }
        }
    }
    public void payDueFreq() {
        for (String l : list) {
            if (i == selection) {
                account = list.get(i);
            } i++;
        }
        for (AccountEvent a_e : account_event) {
            id_orginal = a_e.getId();
        }
        System.out.println("############################   " + account);
        account_num = editTextAccountNumber.getText().toString();
        sum_String = editTextSum.getText().toString();
        sum = Float.parseFloat(sum_String);
        name = editTextName.getText().toString();
        // sum, account_num, time_date
        int lenght = account_num.length();
        if (lenght < 18) {
            textViewPay.setText("No such account");
        } else if (lenght >= 18) {
            for (Debit_account d_a : debit_accounts) {
                if (account.equals(d_a.getAccountNumber())) {
                    payLim = d_a.getPayLim();
                    bal = d_a.getBalance();
                    credit_lim = d_a.getPayLim();
                    if (sum <= payLim) {
                        b++;
                        if ((bal - (sum * freq_Int)) > 0) {
                            a++;
                        }
                    }
                }
            }
            for (Credit_account c_a : credit_accounts) {
                if (account.equals(c_a.getAccountNumber())) {
                    payLim = c_a.getPayLim();
                    bal = c_a.getBalance();
                    if (sum <= payLim) {
                        b++;
                        if ((bal - (sum * freq_Int)) > credit_lim) {
                            a++;
                        }
                    }
                }
            }
            if (a == 0 && b > 0) {
                textViewPay.setText("Account balance is not enough");
            } else if (a == 0 && b == 0) {
                textViewPay.setText("Amount is larger than paylimit");
            } else if (a > 0) {
                //Adding event list
                //Freq-list
                freq_String = editTextFreq.getText().toString();
                freq_Int = Integer.parseInt(freq_String);

                if (freq_Int <= 0) {
                    textViewPay.setText("Not possible option.");
                } else if (freq_Int > 12) {
                    textViewPay.setText("Only possible to make 1-12 automatic account payments.");
                } else if (freq_Int < 13 && freq_Int > 0) {
                    for (i = 0; i < freq_Int; i++) {
                        String yDate = Integer.toString(yearInt);
                        String mDate = Integer.toString(monthInt + 1);
                        if (mDate.equals("1")) {
                            mDate = "01";
                        } else if (mDate.equals("2")) {
                            mDate = "02";
                        } else if (mDate.equals("3")) {
                            mDate = "03";
                        } else if (mDate.equals("4")) {
                            mDate = "04";
                        } else if (mDate.equals("5")) {
                            mDate = "05";
                        } else if (mDate.equals("6")) {
                            mDate = "06";
                        } else if (mDate.equals("7")) {
                            mDate = "07";
                        } else if (mDate.equals("8")) {
                            mDate = "08";
                        } else if (mDate.equals("9")) {
                            mDate = "09";
                        }
                        String dDate = Integer.toString(dayInt);
                        if (dDate.equals("1")) {
                            dDate = "01";
                        } else if (dDate.equals("2")) {
                            dDate = "02";
                        } else if (dDate.equals("3")) {
                            dDate = "03";
                        } else if (dDate.equals("4")) {
                            dDate = "04";
                        } else if (dDate.equals("5")) {
                            dDate = "05";
                        } else if (dDate.equals("6")) {
                            dDate = "06";
                        } else if (dDate.equals("7")) {
                            dDate = "07";
                        } else if (dDate.equals("8")) {
                            dDate = "08";
                        } else if (dDate.equals("9")) {
                            dDate = "09";
                        }
                        date_Freq_String = yDate + mDate + dDate;
                        dateInt = Integer.parseInt(date_Freq_String);
                        if ((monthInt + 1) >= 13) {
                            yearInt++;
                            monthInt = 0;
                            paylog.add(new PayLog(userIdSelection, dateInt, sum, account, userNameSelection, account_num, name));
                            monthInt++;
                            System.out.println("DATE:::::::::::::::::::::::::::: " + dateInt);
                        } else if ((monthInt) < 13) {
                            paylog.add(new PayLog(userIdSelection, dateInt, sum, account, userNameSelection, account_num, name));
                            monthInt++;
                            System.out.println("DATE:::::::::::::::::::::::::::: " + dateInt);
                        }
                    } textViewPay.setText("Set to due date for " + freq_Int + " times.");
                }
            }
        }
    }
    public void payDue() {
        for (String l : list) {
            if (i == selection) {
                account = list.get(i);
            } i++;
        }
        for (AccountEvent a_e : account_event) {
            id_orginal = a_e.getId();
        }
        System.out.println("############################   " + account);
        account_num = editTextAccountNumber.getText().toString();
        sum_String = editTextSum.getText().toString();
        sum = Float.parseFloat(sum_String);
        name = editTextName.getText().toString();
        // sum, account_num, time_date
        int lenght = account_num.length();
        if (lenght < 18) {
            textViewPay.setText("No such account");
        } else if (lenght >= 18) {
            for (Debit_account d_a : debit_accounts) {
                if (account.equals(d_a.getAccountNumber())) {
                    payLim = d_a.getPayLim();
                    bal = d_a.getBalance();
                    credit_lim = d_a.getPayLim();
                    if (sum <= payLim) {
                        b++;
                        if ((bal - sum) > 0) {
                            a++;
                        }
                    }
                }
            }
            for (Credit_account c_a : credit_accounts) {
                if (account.equals(c_a.getAccountNumber())) {
                    payLim = c_a.getPayLim();
                    bal = c_a.getBalance();
                    if (sum <= payLim) {
                        b++;
                        if ((bal - sum) > credit_lim) {
                            a++;
                        }
                    }
                }
            }
            if (a == 0 && b > 0) {
                textViewPay.setText("Account balance is not enough");
            } else if (a == 0 && b == 0) {
                textViewPay.setText("Amount is larger than paylimit");
            } else if (a > 0) {

                //Adding event list

                paylog.add(new PayLog(userIdSelection, date_, sum, account, userNameSelection, account_num, name));
                Toast.makeText(getApplicationContext(), "Payment set to due date", Toast.LENGTH_SHORT).show();


            }
        }
    }
    public void payFreq() {

        freq_String = editTextFreq.getText().toString();
        freq_Int = Integer.parseInt(freq_String);

        if (freq_Int <= 0) {
            Toast.makeText(getApplicationContext(), "Not possible option.", Toast.LENGTH_SHORT).show();
        } else if (freq_Int > 12) {
            Toast.makeText(getApplicationContext(), "Only possible to make 1-12 automatic account payments.", Toast.LENGTH_SHORT).show();

        } else if (freq_Int < 13 && freq_Int > 0) {
            payNormal();
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat(("dd-MM-yyyy"));
            String formattedDate = df.format(c);
            String[] separated = formattedDate.split("-");

            dayString = separated[0];
            monthString = separated[1];
            if (monthString.equals("01")) {
                monthString = "1";
            } else if (monthString.equals("02")) {
                monthString = "2";
            } else if (monthString.equals("03")) {
                monthString = "3";
            } else if (monthString.equals("04")) {
                monthString = "4";
            } else if (monthString.equals("05")) {
                monthString = "5";
            } else if (monthString.equals("06")) {
                monthString = "6";
            } else if (monthString.equals("07")) {
                monthString = "7";
            } else if (monthString.equals("08")) {
                monthString = "8";
            } else if (monthString.equals("09")) {
                monthString = "9";
            }
            yearString = separated[2];

            int dayIntF = Integer.parseInt(dayString);
            int monthIntF = Integer.parseInt(monthString) + 1;
            int yearIntF = Integer.parseInt(yearString);

            dateString = (yearString + monthString + dayString);
            dateInt = Integer.parseInt(dateString);
            System.out.println("¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    " + dateInt);

            for (i = 0; i < freq_Int; i++) {
                String yDate = Integer.toString(yearIntF);
                String mDate = Integer.toString(monthIntF);
                if (mDate.equals("1")) {
                    mDate = "01";
                } else if (mDate.equals("2")) {
                    mDate = "02";
                } else if (mDate.equals("3")) {
                    mDate = "03";
                } else if (mDate.equals("4")) {
                    mDate = "04";
                } else if (mDate.equals("5")) {
                    mDate = "05";
                } else if (mDate.equals("6")) {
                    mDate = "06";
                } else if (mDate.equals("7")) {
                    mDate = "07";
                } else if (mDate.equals("8")) {
                    mDate = "08";
                } else if (mDate.equals("9")) {
                    mDate = "09";
                }
                String dDate = Integer.toString(dayIntF);
                if (dDate.equals("1")) {
                    dDate = "01";
                } else if (dDate.equals("2")) {
                    dDate = "02";
                } else if (dDate.equals("3")) {
                    dDate = "03";
                } else if (dDate.equals("4")) {
                    dDate = "04";
                } else if (dDate.equals("5")) {
                    dDate = "05";
                } else if (dDate.equals("6")) {
                    dDate = "06";
                } else if (dDate.equals("7")) {
                    dDate = "07";
                } else if (dDate.equals("8")) {
                    dDate = "08";
                } else if (dDate.equals("9")) {
                    dDate = "09";
                }
                date_Freq_String = yDate + mDate + dDate;
                dateInt = Integer.parseInt(date_Freq_String);
                if ((monthIntF + 1) >= 13) {
                    yearIntF++;
                    monthIntF = 0;
                    paylog.add(new PayLog(userIdSelection, dateInt, sum, account, userNameSelection, account_num, name));
                    monthIntF++;
                    System.out.println("DATE:::::::::::::::::::::::::::: " + dateInt);
                } else if ((monthIntF) < 13) {
                    paylog.add(new PayLog(userIdSelection, dateInt, sum, account, userNameSelection, account_num, name));
                    monthIntF++;
                    System.out.println("DATE:::::::::::::::::::::::::::: " + dateInt);
                }
            }
            Toast.makeText(getApplicationContext(), "Payment succesfull & set to due date", Toast.LENGTH_SHORT).show();
        }
    }
    public void payNormal() {
        for (String l : list) {
            if (i == selection) {
                account = list.get(i);
            } i++;
        }
        for (AccountEvent a_e : account_event) {
            id_orginal = a_e.getId();
        }
        System.out.println("############################   " + account);
        Date time = Calendar.getInstance().getTime();
        time_date = time.toString();

        account_num = editTextAccountNumber.getText().toString();
        sum_String = editTextSum.getText().toString();
        sum = Float.parseFloat(sum_String);
        name = editTextName.getText().toString();
        // sum, account_num, time_date
        int lenght = account_num.length();
        if (lenght < 18) {
            textViewPay.setText("No such account");
        } else if (lenght >= 18) {
            for (Debit_account d_a : debit_accounts) {
                if (account.equals(d_a.getAccountNumber())) {
                    payLim = d_a.getPayLim();
                    bal = d_a.getBalance();
                    credit_lim = d_a.getPayLim();
                    if (sum <= payLim) {
                        b++;
                        if ((bal - sum) > 0) {
                            d_a.pay(sum);
                            a++;
                        }
                    }
                }
            }
            for (Credit_account c_a : credit_accounts) {
                if (account.equals(c_a.getAccountNumber())) {
                    payLim = c_a.getPayLim();
                    bal = c_a.getBalance();
                    if (sum <= payLim) {
                        b++;
                        if ((bal - sum) > credit_lim) {
                            c_a.pay(sum);
                            a++;
                        }
                    }
                }
            }
            if (a == 0 && b > 0) {
                textViewPay.setText("Account balance is not enough");
            } else if (a == 0 && b == 0) {
                textViewPay.setText("Amount is larger than paylimit");
            } else if (a > 0) {
                for (Debit_account d_a : debit_accounts) {
                    if (d_a.getAccountNumber().equals(account_num)) {
                        d_a.addMoney(sum);
                    }
                }
                for (Credit_account c_a : credit_accounts) {
                    if (c_a.getAccountNumber().equals(account_num)) {
                        c_a.addMoney(sum);
                    }
                }
                //Adding event list
                account_event.add(new AccountEvent(id_orginal + 1, account, time_date, -(sum), account_num, name));
                account_event.add(new AccountEvent(id_orginal + 2, account_num, time_date, sum, account, userNameSelection));
                Toast.makeText(getApplicationContext(), "Payment succesfull", Toast.LENGTH_SHORT).show();

            }
        }
    }
    //spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selection = parent.getSelectedItemPosition();

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearInt = year;
        monthInt = month;
        dayInt = dayOfMonth;
        year_ = Integer.toString(year);
        month_ = Integer.toString((month + 1));
        if (month_.equals("1")) {
            month_ = "01";
        } else if (month_.equals("2")) {
            month_ = "02";
        } else if (month_.equals("3")) {
            month_ = "03";
        } else if (month_.equals("4")) {
            month_ = "04";
        } else if (month_.equals("5")) {
            month_ = "05";
        } else if (month_.equals("6")) {
            month_ = "06";
        } else if (month_.equals("7")) {
            month_ = "07";
        } else if (month_.equals("8")) {
            month_ = "08";
        } else if (month_.equals("9")) {
            month_ = "09";
        }
        day_ = Integer.toString(dayOfMonth);
        if (day_.equals("1")) {
            day_ = "01";
        } else if (day_.equals("2")) {
            day_ = "02";
        } else if (day_.equals("3")) {
            day_ = "03";
        } else if (day_.equals("4")) {
            day_ = "04";
        } else if (day_.equals("5")) {
            day_ = "05";
        } else if (day_.equals("6")) {
            day_ = "06";
        } else if (day_.equals("7")) {
            day_ = "07";
        } else if (day_.equals("8")) {
            day_ = "08";
        } else if (day_.equals("9")) {
            day_ = "09";
        }
        date_selection = year_ + month_ + day_;
        date_ = Integer.parseInt(date_selection);
        textViewPay.setText(year + ", " + (month + 1) + ", " + dayOfMonth);
    }

    public void upComingPayments(View v){
        String acc = spinner2.getSelectedItem().toString();
        selectedAccNum = acc;
        Intent intent = new Intent(PaymentActivity.this, UpcomingPaymentActivity.class);
        startActivity(intent);
    }
}

