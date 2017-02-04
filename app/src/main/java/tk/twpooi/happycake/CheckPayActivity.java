package tk.twpooi.happycake;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.FadeEnter.FadeEnter;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;

public class CheckPayActivity extends BaseActivity {

    public static final int SELECT_SHOP = 1000;

    // UI
    private ImageView closeBtn;
    private TextView tv_shop;
    private TextView tv_selectShopBtn;
    private TextView pickDateBtn;
    private TextView pickTimeBtn;
    private EditText editPickPerson;
    private TextView tv_tele_1;
    private EditText tv_tele_2;
    private TextView tv_cakeTitle;
    private TextView tv_price;
    private TextView tv_totalPrice;
    private TextView payBtn;

    // Data
    private HashMap<String, Object> data;
    private String shop;
    private String date;
    private String time;
    private String person;
    private String phoneNumber;
    private String cakeTitle;
    private String price;
    private String totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_pay);

        data = (HashMap<String, Object>)getIntent().getSerializableExtra("data");

        price = (String)data.get("price");
        totalPrice = price;
        cakeTitle = String.format("[%s] %s %s베이스 생크림 케이크", (String)data.get("shop"), (String)data.get("size"), (String)data.get("spon"));

        InitData();

        InitUI();


    }

    private void InitData(){



    }

    private void InitUI(){

        closeBtn = (ImageView)findViewById(R.id.close_btn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tv_shop = (TextView)findViewById(R.id.tv_shop);
        tv_selectShopBtn = (TextView)findViewById(R.id.tv_select_shop_btn);
        tv_selectShopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectShopActivity.class);
                intent.putExtra("code", SELECT_SHOP);
                startActivityForResult(intent, SELECT_SHOP);
            }
        });
        pickDateBtn = (TextView)findViewById(R.id.pick_date_btn);
        pickDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                date = String.format("%d년 %d월 %d일", year, monthOfYear+1, dayOfMonth);
                                pickDateBtn.setText(date);
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "날짜선택");
            }
        });
        pickTimeBtn = (TextView)findViewById(R.id.pick_time_btn);
        pickTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                time = String.format("%d시 %d분", hourOfDay, minute);
                                pickTimeBtn.setText(time);
                            }
                        },
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );
                tpd.show(getFragmentManager(), "시간선택");
            }
        });
        editPickPerson = (EditText)findViewById(R.id.edit_pick_person);
        tv_tele_1 = (TextView)findViewById(R.id.tv_tele_1);
        tv_tele_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListDialog();
            }
        });
        tv_tele_2 = (EditText)findViewById(R.id.tv_tele_2);
        tv_cakeTitle = (TextView)findViewById(R.id.tv_cake_title);
        tv_cakeTitle.setText(cakeTitle);
        tv_price = (TextView)findViewById(R.id.tv_price);
        tv_price.setText(price);
        tv_totalPrice = (TextView)findViewById(R.id.tv_total_price);
        tv_totalPrice.setText(totalPrice);
        payBtn = (TextView)findViewById(R.id.pay_btn);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPayAlert();
            }
        });
        setFont(payBtn, Bareun3);

    }

    public void showListDialog() {
        final String[] list = {
                "010",
                "011",
                "016",
                "017",
                "018",
                "019"
        };
        final NormalListDialog dialog = new NormalListDialog(this, list);
        dialog.title("선택")
                .titleTextSize_SP(14.5f)
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_tele_1.setText(list[position]);
                dialog.dismiss();
            }
        });

    }

    private void ShowPayAlert(){

        shop = tv_shop.getText().toString();
        person = editPickPerson.getText().toString();
        phoneNumber = tv_tele_1.getText().toString() + tv_tele_2.getText().toString();
        cakeTitle = tv_cakeTitle.getText().toString();
        price = tv_price.getText().toString();
        totalPrice = tv_totalPrice.getText().toString();

        String msg =
                "매장 : " + shop + "\n" +
                "픽업일 : " + date + "\n" +
                "픽업시간 : " + time + "\n" +
                "수령자 : " + person + "\n" +
                "휴대폰 : " + phoneNumber + "\n" +
                "케익명 : " + cakeTitle + "\n" +
                "총 결제금액 : " + totalPrice;


        final MaterialDialog dialog = new MaterialDialog(CheckPayActivity.this);
        dialog.content(msg)
                .title("결제 정보 확인")
                .btnText("취소", "확인")
                .showAnim(new FadeEnter())
                .show();
        OnBtnClickL left = new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                dialog.dismiss();
            }
        };
        OnBtnClickL right = new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                dialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                intent.putExtra("isNoLoading", true);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        };
        dialog.setOnBtnClickL(left, right);

    }

    public static String Comma_won(String junsu) {
        int inValues = Integer.parseInt(junsu);
        DecimalFormat Commas = new DecimalFormat("#,###");
        String result_int = (String)Commas.format(inValues);
        return result_int;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case SELECT_SHOP:
                shop = data.getStringExtra("title");
                tv_shop.setText(shop);
                break;
            default:
                break;
        }
    }


}
