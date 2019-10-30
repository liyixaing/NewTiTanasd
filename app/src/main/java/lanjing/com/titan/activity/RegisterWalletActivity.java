package lanjing.com.titan.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.RegexUtils;
import com.lxh.baselibray.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.SetHelpContact;
import lanjing.com.titan.response.SetHelpResponse;
import retrofit2.Response;

/**
 * 注册（创建钱包   第二步  选择助记词）
 */
public class RegisterWalletActivity extends MvpActivity<SetHelpContact.SetHelpPresent> implements SetHelpContact.ISetHelpView {
    @BindView(R.id.word_one)
    TextView wordOne;
    @BindView(R.id.word_two)
    TextView wordTwo;
    @BindView(R.id.word_three)
    TextView wordThree;
    @BindView(R.id.one_word)
    TextView oneWord;
    @BindView(R.id.two_word)
    TextView twoWord;
    @BindView(R.id.three_word)
    TextView threeWord;
    @BindView(R.id.four_word)
    TextView fourWord;
    @BindView(R.id.five_word)
    TextView fiveWord;
    @BindView(R.id.six_word)
    TextView sixWord;
    @BindView(R.id.seven_word)
    TextView sevenWord;
    @BindView(R.id.eight_word)
    TextView eightWord;
    @BindView(R.id.nine_word)
    TextView nineWord;
    @BindView(R.id.ten_word)
    TextView tenWord;
    @BindView(R.id.eleven_word)
    TextView elevenWord;
    @BindView(R.id.twelve_word)
    TextView twelveWord;
    @BindView(R.id.next_step_btn)
    TextView nextStepBtn;

    String key;

    @Override
    public void initData(Bundle savedInstanceState) {
        key = getIntent().getStringExtra("key");

        List alist = (List<Object>) getIntent().getSerializableExtra("wordList");//通过key来获取你传输的list集合数据，并强转为List<Object>格式，Object就是前面红色字体部分说的，要实现Serializable接口。
        oneWord.setText(alist.get(0).toString());
        twoWord.setText(alist.get(1).toString());
        threeWord.setText(alist.get(2).toString());
        fourWord.setText(alist.get(3).toString());
        fiveWord.setText(alist.get(4).toString());
        sixWord.setText(alist.get(5).toString());
        sevenWord.setText(alist.get(6).toString());
        eightWord.setText(alist.get(7).toString());
        nineWord.setText(alist.get(8).toString());
        tenWord.setText(alist.get(9).toString());
        elevenWord.setText(alist.get(10).toString());
        twelveWord.setText(alist.get(11).toString());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_wallet;
    }

    public void initWord() {
        String word1 = wordOne.getText().toString().trim();
        String word2 = wordTwo.getText().toString().trim();
        String word3 = wordThree.getText().toString().trim();
        if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
            oneWord.setEnabled(false);
            twoWord.setEnabled(false);
            threeWord.setEnabled(false);
            fourWord.setEnabled(false);
            fiveWord.setEnabled(false);
            sixWord.setEnabled(false);
            sevenWord.setEnabled(false);
            eightWord.setEnabled(false);
            nineWord.setEnabled(false);
            tenWord.setEnabled(false);
            elevenWord.setEnabled(false);
            twelveWord.setEnabled(false);
        }
    }

    public void initWord2() {
        String word1 = wordOne.getText().toString().trim();
        String word2 = wordTwo.getText().toString().trim();
        String word3 = wordThree.getText().toString().trim();
        if (ObjectUtils.isEmpty(word1) && ObjectUtils.isEmpty(word2) && ObjectUtils.isEmpty(word3)) {
            oneWord.setEnabled(true);
            twoWord.setEnabled(true);
            threeWord.setEnabled(true);
            fourWord.setEnabled(true);
            fiveWord.setEnabled(true);
            sixWord.setEnabled(true);
            sevenWord.setEnabled(true);
            eightWord.setEnabled(true);
            nineWord.setEnabled(true);
            tenWord.setEnabled(true);
            elevenWord.setEnabled(true);
            twelveWord.setEnabled(true);
        }
    }


    @OnClick({R.id.word_one, R.id.word_two, R.id.word_three, R.id.one_word, R.id.two_word, R.id.three_word, R.id.four_word, R.id.five_word, R.id.six_word, R.id.seven_word, R.id.eight_word, R.id.nine_word, R.id.ten_word, R.id.eleven_word, R.id.twelve_word, R.id.next_step_btn})
    public void onViewClicked(View view) {
        Drawable icon_clear = getResources().getDrawable(R.mipmap.icon_clear);
        icon_clear.setBounds(0, 0, icon_clear.getMinimumWidth(), icon_clear.getMinimumHeight());
        Drawable icon_hide_clear = getResources().getDrawable(R.mipmap.icon_hide_clear);
        icon_hide_clear.setBounds(0, 0, icon_hide_clear.getMinimumWidth(), icon_hide_clear.getMinimumHeight());
        String word1 = wordOne.getText().toString().trim();
        String word2 = wordTwo.getText().toString().trim();
        String word3 = wordThree.getText().toString().trim();

        initWord();
        switch (view.getId()) {
            case R.id.word_one://清空第一个助记词
                wordOne.setCompoundDrawables(null, null, icon_hide_clear, null);
                initWord2();
                if (wordOne.getText().toString().equals(oneWord.getText().toString())) {
                    oneWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    oneWord.setTextColor(getResources().getColor((R.color.blue)));
                    oneWord.setEnabled(true);
                } else if (wordOne.getText().toString().equals(twoWord.getText().toString())) {
                    twoWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    twoWord.setTextColor(getResources().getColor((R.color.blue)));
                    twoWord.setEnabled(true);
                } else if (wordOne.getText().toString().equals(threeWord.getText().toString())) {
                    threeWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    threeWord.setTextColor(getResources().getColor((R.color.blue)));
                    threeWord.setEnabled(true);
                } else if (wordOne.getText().toString().equals(fourWord.getText().toString())) {
                    fourWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    fourWord.setTextColor(getResources().getColor((R.color.blue)));
                    fourWord.setEnabled(true);
                } else if (wordOne.getText().toString().equals(fiveWord.getText().toString())) {
                    fiveWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    fiveWord.setTextColor(getResources().getColor((R.color.blue)));
                    fiveWord.setEnabled(true);
                } else if (wordOne.getText().toString().equals(sixWord.getText().toString())) {
                    sixWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    sixWord.setTextColor(getResources().getColor((R.color.blue)));
                    sixWord.setEnabled(true);
                } else if (wordOne.getText().toString().equals(sevenWord.getText().toString())) {
                    sevenWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    sevenWord.setTextColor(getResources().getColor((R.color.blue)));
                    sevenWord.setEnabled(true);
                } else if (wordOne.getText().toString().equals(eightWord.getText().toString())) {
                    eightWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    eightWord.setTextColor(getResources().getColor((R.color.blue)));
                    eightWord.setEnabled(true);
                } else if (wordOne.getText().toString().equals(nineWord.getText().toString())) {
                    nineWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    nineWord.setTextColor(getResources().getColor((R.color.blue)));
                    nineWord.setEnabled(true);
                } else if (wordOne.getText().toString().equals(tenWord.getText().toString())) {
                    tenWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    tenWord.setTextColor(getResources().getColor((R.color.blue)));
                    tenWord.setEnabled(true);
                } else if (wordOne.getText().toString().equals(elevenWord.getText().toString())) {
                    elevenWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    elevenWord.setTextColor(getResources().getColor((R.color.blue)));
                    elevenWord.setEnabled(true);
                } else if (wordOne.getText().toString().equals(twelveWord.getText().toString())) {
                    twelveWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    twelveWord.setTextColor(getResources().getColor((R.color.blue)));
                    twelveWord.setEnabled(true);
                }
                twoWord.setEnabled(true);
                threeWord.setEnabled(true);
                fourWord.setEnabled(true);
                fiveWord.setEnabled(true);
                sixWord.setEnabled(true);
                sevenWord.setEnabled(true);
                eightWord.setEnabled(true);
                nineWord.setEnabled(true);
                tenWord.setEnabled(true);
                elevenWord.setEnabled(true);
                twelveWord.setEnabled(true);
                wordOne.setText("");
                break;
            case R.id.word_two://清空第二个助记词
                initWord2();
                wordTwo.setCompoundDrawables(null, null, icon_hide_clear, null);
                if (wordTwo.getText().toString().equals(oneWord.getText().toString())) {
                    oneWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    oneWord.setTextColor(getResources().getColor((R.color.blue)));
                    oneWord.setEnabled(true);
                } else if (wordTwo.getText().toString().equals(twoWord.getText().toString())) {
                    twoWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    twoWord.setTextColor(getResources().getColor((R.color.blue)));
                    twoWord.setEnabled(true);
                } else if (wordTwo.getText().toString().equals(threeWord.getText().toString())) {
                    threeWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    threeWord.setTextColor(getResources().getColor((R.color.blue)));
                    threeWord.setEnabled(true);
                } else if (wordTwo.getText().toString().equals(fourWord.getText().toString())) {
                    fourWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    fourWord.setTextColor(getResources().getColor((R.color.blue)));
                    fourWord.setEnabled(true);
                } else if (wordTwo.getText().toString().equals(fiveWord.getText().toString())) {
                    fiveWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    fiveWord.setTextColor(getResources().getColor((R.color.blue)));
                    fiveWord.setEnabled(true);
                } else if (wordTwo.getText().toString().equals(sixWord.getText().toString())) {
                    sixWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    sixWord.setTextColor(getResources().getColor((R.color.blue)));
                    sixWord.setEnabled(true);
                } else if (wordTwo.getText().toString().equals(sevenWord.getText().toString())) {
                    sevenWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    sevenWord.setTextColor(getResources().getColor((R.color.blue)));
                    sevenWord.setEnabled(true);
                } else if (wordTwo.getText().toString().equals(eightWord.getText().toString())) {
                    eightWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    eightWord.setTextColor(getResources().getColor((R.color.blue)));
                    eightWord.setEnabled(true);
                } else if (wordTwo.getText().toString().equals(nineWord.getText().toString())) {
                    nineWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    nineWord.setTextColor(getResources().getColor((R.color.blue)));
                    nineWord.setEnabled(true);
                } else if (wordTwo.getText().toString().equals(tenWord.getText().toString())) {
                    tenWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    tenWord.setTextColor(getResources().getColor((R.color.blue)));
                    tenWord.setEnabled(true);
                } else if (wordTwo.getText().toString().equals(elevenWord.getText().toString())) {
                    elevenWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    elevenWord.setTextColor(getResources().getColor((R.color.blue)));
                    elevenWord.setEnabled(true);
                } else if (wordTwo.getText().toString().equals(twelveWord.getText().toString())) {
                    twelveWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    twelveWord.setTextColor(getResources().getColor((R.color.blue)));
                    twelveWord.setEnabled(true);
                }
                oneWord.setEnabled(true);
                threeWord.setEnabled(true);
                fourWord.setEnabled(true);
                fiveWord.setEnabled(true);
                sixWord.setEnabled(true);
                sevenWord.setEnabled(true);
                eightWord.setEnabled(true);
                nineWord.setEnabled(true);
                tenWord.setEnabled(true);
                elevenWord.setEnabled(true);
                twelveWord.setEnabled(true);
                wordTwo.setText("");
                break;
            case R.id.word_three://清空第三个助记词
                initWord2();
                wordThree.setCompoundDrawables(null, null, icon_hide_clear, null);
                if (wordThree.getText().toString().equals(oneWord.getText().toString())) {
                    oneWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    oneWord.setTextColor(getResources().getColor((R.color.blue)));
                    oneWord.setEnabled(true);
                } else if (wordThree.getText().toString().equals(twoWord.getText().toString())) {
                    twoWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    twoWord.setTextColor(getResources().getColor((R.color.blue)));
                    twoWord.setEnabled(true);
                } else if (wordThree.getText().toString().equals(threeWord.getText().toString())) {
                    threeWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    threeWord.setTextColor(getResources().getColor((R.color.blue)));
                    threeWord.setEnabled(true);
                } else if (wordThree.getText().toString().equals(fourWord.getText().toString())) {
                    fourWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    fourWord.setTextColor(getResources().getColor((R.color.blue)));
                    fourWord.setEnabled(true);
                } else if (wordThree.getText().toString().equals(fiveWord.getText().toString())) {
                    fiveWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    fiveWord.setTextColor(getResources().getColor((R.color.blue)));
                    fiveWord.setEnabled(true);
                } else if (wordThree.getText().toString().equals(sixWord.getText().toString())) {
                    sixWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    sixWord.setTextColor(getResources().getColor((R.color.blue)));
                    sixWord.setEnabled(true);
                } else if (wordThree.getText().toString().equals(sevenWord.getText().toString())) {
                    sevenWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    sevenWord.setTextColor(getResources().getColor((R.color.blue)));
                    sevenWord.setEnabled(true);
                } else if (wordThree.getText().toString().equals(eightWord.getText().toString())) {
                    eightWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    eightWord.setTextColor(getResources().getColor((R.color.blue)));
                    eightWord.setEnabled(true);
                } else if (wordThree.getText().toString().equals(nineWord.getText().toString())) {
                    nineWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    nineWord.setTextColor(getResources().getColor((R.color.blue)));
                    nineWord.setEnabled(true);
                } else if (wordThree.getText().toString().equals(tenWord.getText().toString())) {
                    tenWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    tenWord.setTextColor(getResources().getColor((R.color.blue)));
                    tenWord.setEnabled(true);
                } else if (wordThree.getText().toString().equals(elevenWord.getText().toString())) {
                    elevenWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    elevenWord.setTextColor(getResources().getColor((R.color.blue)));
                    elevenWord.setEnabled(true);
                } else if (wordThree.getText().toString().equals(twelveWord.getText().toString())) {
                    twelveWord.setBackground(getDrawable(R.drawable.text_onclick_blue_bg_stroke));
                    twelveWord.setTextColor(getResources().getColor((R.color.blue)));
                    twelveWord.setEnabled(true);
                }
                oneWord.setEnabled(true);
                twoWord.setEnabled(true);
                fourWord.setEnabled(true);
                fiveWord.setEnabled(true);
                sixWord.setEnabled(true);
                sevenWord.setEnabled(true);
                eightWord.setEnabled(true);
                nineWord.setEnabled(true);
                tenWord.setEnabled(true);
                elevenWord.setEnabled(true);
                twelveWord.setEnabled(true);
                wordThree.setText("");
                break;
            case R.id.one_word://  助记词  1
                if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                    oneWord.setEnabled(false);
                    twoWord.setEnabled(false);
                    threeWord.setEnabled(false);
                    fourWord.setEnabled(false);
                    fiveWord.setEnabled(false);
                    sixWord.setEnabled(false);
                    sevenWord.setEnabled(false);
                    eightWord.setEnabled(false);
                    nineWord.setEnabled(false);
                    tenWord.setEnabled(false);
                    elevenWord.setEnabled(false);
                    twelveWord.setEnabled(false);
                } else {
                    if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                        if (word1.equals(word2) || word1.equals(word3) || word2.equals(word3)) {
                            ToastUtils.showLongToast(context, getResources().getString(R.string.noNon_repeatable));//输出不可重复选着
                            return;
                        }
                        return;
                    } else {
                        if (ObjectUtils.isEmpty(wordOne.getText().toString())) {
                            wordOne.setText(oneWord.getText().toString().trim());
                            wordOne.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordTwo.getText().toString())) {
                            wordTwo.setText(oneWord.getText().toString().trim());
                            wordTwo.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordThree.getText().toString())) {
                            wordThree.setText(oneWord.getText().toString().trim());
                            wordThree.setCompoundDrawables(null, null, icon_clear, null);
                        }
                        oneWord.setBackground(getDrawable(R.drawable.shape_blue_bg));
                        oneWord.setTextColor(getResources().getColor((R.color.white)));
                        oneWord.setEnabled(false);
                    }
                }


                break;
            case R.id.two_word://  助记词  2
                if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                    oneWord.setEnabled(false);
                    twoWord.setEnabled(false);
                    threeWord.setEnabled(false);
                    fourWord.setEnabled(false);
                    fiveWord.setEnabled(false);
                    sixWord.setEnabled(false);
                    sevenWord.setEnabled(false);
                    eightWord.setEnabled(false);
                    nineWord.setEnabled(false);
                    tenWord.setEnabled(false);
                    elevenWord.setEnabled(false);
                    twelveWord.setEnabled(false);
                } else {
                    if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) || !ObjectUtils.isEmpty(word3)) {
                        if (word1.equals(word2) || word1.equals(word3) || word2.equals(word3)) {
                            ToastUtils.showLongToast(context, getResources().getString(R.string.noNon_repeatable));//不可重复选择
                        }
                        return;
                    } else {
                        if (ObjectUtils.isEmpty(wordOne.getText().toString())) {
                            wordOne.setText(twoWord.getText().toString().trim());
                            wordOne.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordTwo.getText().toString())) {
                            wordTwo.setText(twoWord.getText().toString().trim());
                            wordTwo.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordThree.getText().toString())) {
                            wordThree.setText(twoWord.getText().toString().trim());
                            wordThree.setCompoundDrawables(null, null, icon_clear, null);
                        }
                        twoWord.setBackground(getDrawable(R.drawable.shape_blue_bg));
                        twoWord.setTextColor(getResources().getColor((R.color.white)));
                        twoWord.setEnabled(false);
                    }

                }

                break;
            case R.id.three_word://  助记词  3
                if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                    oneWord.setEnabled(false);
                    twoWord.setEnabled(false);
                    threeWord.setEnabled(false);
                    fourWord.setEnabled(false);
                    fiveWord.setEnabled(false);
                    sixWord.setEnabled(false);
                    sevenWord.setEnabled(false);
                    eightWord.setEnabled(false);
                    nineWord.setEnabled(false);
                    tenWord.setEnabled(false);
                    elevenWord.setEnabled(false);
                    twelveWord.setEnabled(false);
                } else {
                    if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                        if (word1.equals(word2) || word1.equals(word3) || word2.equals(word3)) {
                            ToastUtils.showLongToast(context, getResources().getString(R.string.noNon_repeatable));
                        }
                        return;
                    } else {
                        if (ObjectUtils.isEmpty(wordOne.getText().toString())) {
                            wordOne.setText(threeWord.getText().toString().trim());
                            wordOne.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordTwo.getText().toString())) {
                            wordTwo.setText(threeWord.getText().toString().trim());
                            wordTwo.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordThree.getText().toString())) {
                            wordThree.setText(threeWord.getText().toString().trim());
                            wordThree.setCompoundDrawables(null, null, icon_clear, null);
                        }
                        threeWord.setBackground(getDrawable(R.drawable.shape_blue_bg));
                        threeWord.setTextColor(getResources().getColor((R.color.white)));
                        threeWord.setEnabled(false);
                    }
                }
                break;
            case R.id.four_word://  助记词  4
                if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                    oneWord.setEnabled(false);
                    twoWord.setEnabled(false);
                    threeWord.setEnabled(false);
                    fourWord.setEnabled(false);
                    fiveWord.setEnabled(false);
                    sixWord.setEnabled(false);
                    sevenWord.setEnabled(false);
                    eightWord.setEnabled(false);
                    nineWord.setEnabled(false);
                    tenWord.setEnabled(false);
                    elevenWord.setEnabled(false);
                    twelveWord.setEnabled(false);
                } else {
                    if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                        if (word1.equals(word2) || word1.equals(word3) || word2.equals(word3)) {
                            ToastUtils.showLongToast(context, getResources().getString(R.string.noNon_repeatable));
                        }
                        return;
                    } else {
                        if (ObjectUtils.isEmpty(wordOne.getText().toString())) {
                            wordOne.setText(fourWord.getText().toString().trim());
                            wordOne.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordTwo.getText().toString())) {
                            wordTwo.setText(fourWord.getText().toString().trim());
                            wordTwo.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordThree.getText().toString())) {
                            wordThree.setText(fourWord.getText().toString().trim());
                            wordThree.setCompoundDrawables(null, null, icon_clear, null);
                        }
                        fourWord.setBackground(getDrawable(R.drawable.shape_blue_bg));
                        fourWord.setTextColor(getResources().getColor((R.color.white)));
                        fourWord.setEnabled(false);
                    }
                }
                break;
            case R.id.five_word://  助记词  5
                if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                    oneWord.setEnabled(false);
                    twoWord.setEnabled(false);
                    threeWord.setEnabled(false);
                    fourWord.setEnabled(false);
                    fiveWord.setEnabled(false);
                    sixWord.setEnabled(false);
                    sevenWord.setEnabled(false);
                    eightWord.setEnabled(false);
                    nineWord.setEnabled(false);
                    tenWord.setEnabled(false);
                    elevenWord.setEnabled(false);
                    twelveWord.setEnabled(false);
                } else {
                    if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                        if (word1.equals(word2) || word1.equals(word3) || word2.equals(word3)) {
                            ToastUtils.showLongToast(context, getResources().getString(R.string.noNon_repeatable));
                        }
                        return;
                    } else {
                        if (ObjectUtils.isEmpty(wordOne.getText().toString())) {
                            wordOne.setText(fiveWord.getText().toString().trim());
                            wordOne.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordTwo.getText().toString())) {
                            wordTwo.setText(fiveWord.getText().toString().trim());
                            wordTwo.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordThree.getText().toString())) {
                            wordThree.setText(fiveWord.getText().toString().trim());
                            wordThree.setCompoundDrawables(null, null, icon_clear, null);
                        }
                        fiveWord.setBackground(getDrawable(R.drawable.shape_blue_bg));
                        fiveWord.setTextColor(getResources().getColor((R.color.white)));
                        fiveWord.setEnabled(false);
                    }
                }
                break;
            case R.id.six_word://  助记词  6
                if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                    oneWord.setEnabled(false);
                    twoWord.setEnabled(false);
                    threeWord.setEnabled(false);
                    fourWord.setEnabled(false);
                    fiveWord.setEnabled(false);
                    sixWord.setEnabled(false);
                    sevenWord.setEnabled(false);
                    eightWord.setEnabled(false);
                    nineWord.setEnabled(false);
                    tenWord.setEnabled(false);
                    elevenWord.setEnabled(false);
                    twelveWord.setEnabled(false);
                } else {
                    if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                        if (word1.equals(word2) || word1.equals(word3) || word2.equals(word3)) {
                            ToastUtils.showLongToast(context, getResources().getString(R.string.noNon_repeatable));
                        }
                        return;
                    } else {
                        if (ObjectUtils.isEmpty(wordOne.getText().toString())) {
                            wordOne.setText(sixWord.getText().toString().trim());
                            wordOne.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordTwo.getText().toString())) {
                            wordTwo.setText(sixWord.getText().toString().trim());
                            wordTwo.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordThree.getText().toString())) {
                            wordThree.setText(sixWord.getText().toString().trim());
                            wordThree.setCompoundDrawables(null, null, icon_clear, null);
                        }
                        sixWord.setBackground(getDrawable(R.drawable.shape_blue_bg));
                        sixWord.setTextColor(getResources().getColor((R.color.white)));
                        sixWord.setEnabled(false);
                    }
                }
                break;
            case R.id.seven_word://  助记词  7
                if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                    oneWord.setEnabled(false);
                    twoWord.setEnabled(false);
                    threeWord.setEnabled(false);
                    fourWord.setEnabled(false);
                    fiveWord.setEnabled(false);
                    sixWord.setEnabled(false);
                    sevenWord.setEnabled(false);
                    eightWord.setEnabled(false);
                    nineWord.setEnabled(false);
                    tenWord.setEnabled(false);
                    elevenWord.setEnabled(false);
                    twelveWord.setEnabled(false);
                } else {
                    if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                        if (word1.equals(word2) || word1.equals(word3) || word2.equals(word3)) {
                            ToastUtils.showLongToast(context, getResources().getString(R.string.noNon_repeatable));
                        }
                        return;
                    } else {
                        if (ObjectUtils.isEmpty(wordOne.getText().toString())) {
                            wordOne.setText(sevenWord.getText().toString().trim());
                            wordOne.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordTwo.getText().toString())) {
                            wordTwo.setText(sevenWord.getText().toString().trim());
                            wordTwo.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordThree.getText().toString())) {
                            wordThree.setText(sevenWord.getText().toString().trim());
                            wordThree.setCompoundDrawables(null, null, icon_clear, null);
                        }
                        sevenWord.setBackground(getDrawable(R.drawable.shape_blue_bg));
                        sevenWord.setTextColor(getResources().getColor((R.color.white)));
                        sevenWord.setEnabled(false);
                    }
                }
                break;
            case R.id.eight_word://  助记词  8
                if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                    oneWord.setEnabled(false);
                    twoWord.setEnabled(false);
                    threeWord.setEnabled(false);
                    fourWord.setEnabled(false);
                    fiveWord.setEnabled(false);
                    sixWord.setEnabled(false);
                    sevenWord.setEnabled(false);
                    eightWord.setEnabled(false);
                    nineWord.setEnabled(false);
                    tenWord.setEnabled(false);
                    elevenWord.setEnabled(false);
                    twelveWord.setEnabled(false);
                } else {
                    if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                        if (word1.equals(word2) || word1.equals(word3) || word2.equals(word3)) {
                            ToastUtils.showLongToast(context, getResources().getString(R.string.noNon_repeatable));
                        }
                        return;
                    } else {
                        if (ObjectUtils.isEmpty(wordOne.getText().toString())) {
                            wordOne.setText(eightWord.getText().toString().trim());
                            wordOne.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordTwo.getText().toString())) {
                            wordTwo.setText(eightWord.getText().toString().trim());
                            wordTwo.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordThree.getText().toString())) {
                            wordThree.setText(eightWord.getText().toString().trim());
                            wordThree.setCompoundDrawables(null, null, icon_clear, null);
                        }
                        eightWord.setBackground(getDrawable(R.drawable.shape_blue_bg));
                        eightWord.setTextColor(getResources().getColor((R.color.white)));
                        eightWord.setEnabled(false);
                    }
                }
                break;
            case R.id.nine_word://  助记词  9
                if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                    oneWord.setEnabled(false);
                    twoWord.setEnabled(false);
                    threeWord.setEnabled(false);
                    fourWord.setEnabled(false);
                    fiveWord.setEnabled(false);
                    sixWord.setEnabled(false);
                    sevenWord.setEnabled(false);
                    eightWord.setEnabled(false);
                    nineWord.setEnabled(false);
                    tenWord.setEnabled(false);
                    elevenWord.setEnabled(false);
                    twelveWord.setEnabled(false);
                } else {
                    if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                        if (word1.equals(word2) || word1.equals(word3) || word2.equals(word3)) {
                            ToastUtils.showLongToast(context, getResources().getString(R.string.noNon_repeatable));
                        }
                        return;
                    } else {
                        if (ObjectUtils.isEmpty(wordOne.getText().toString())) {
                            wordOne.setText(nineWord.getText().toString().trim());
                            wordOne.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordTwo.getText().toString())) {
                            wordTwo.setText(nineWord.getText().toString().trim());
                            wordTwo.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordThree.getText().toString())) {
                            wordThree.setText(nineWord.getText().toString().trim());
                            wordThree.setCompoundDrawables(null, null, icon_clear, null);
                        }
                        nineWord.setBackground(getDrawable(R.drawable.shape_blue_bg));
                        nineWord.setTextColor(getResources().getColor((R.color.white)));
                        nineWord.setEnabled(false);
                    }
                }
                break;
            case R.id.ten_word://  助记词  10
                if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                    oneWord.setEnabled(false);
                    twoWord.setEnabled(false);
                    threeWord.setEnabled(false);
                    fourWord.setEnabled(false);
                    fiveWord.setEnabled(false);
                    sixWord.setEnabled(false);
                    sevenWord.setEnabled(false);
                    eightWord.setEnabled(false);
                    nineWord.setEnabled(false);
                    tenWord.setEnabled(false);
                    elevenWord.setEnabled(false);
                    twelveWord.setEnabled(false);
                } else {
                    if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                        if (word1.equals(word2) || word1.equals(word3) || word2.equals(word3)) {
                            ToastUtils.showLongToast(context, getResources().getString(R.string.noNon_repeatable));
                        }
                        return;
                    } else {
                        if (ObjectUtils.isEmpty(wordOne.getText().toString())) {
                            wordOne.setText(tenWord.getText().toString().trim());
                            wordOne.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordTwo.getText().toString())) {
                            wordTwo.setText(tenWord.getText().toString().trim());
                            wordTwo.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordThree.getText().toString())) {
                            wordThree.setText(tenWord.getText().toString().trim());
                            wordThree.setCompoundDrawables(null, null, icon_clear, null);
                        }
                        tenWord.setBackground(getDrawable(R.drawable.shape_blue_bg));
                        tenWord.setTextColor(getResources().getColor((R.color.white)));
                        tenWord.setEnabled(false);
                    }
                }
                break;
            case R.id.eleven_word://  助记词  11
                if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                    oneWord.setEnabled(false);
                    twoWord.setEnabled(false);
                    threeWord.setEnabled(false);
                    fourWord.setEnabled(false);
                    fiveWord.setEnabled(false);
                    sixWord.setEnabled(false);
                    sevenWord.setEnabled(false);
                    eightWord.setEnabled(false);
                    nineWord.setEnabled(false);
                    tenWord.setEnabled(false);
                    elevenWord.setEnabled(false);
                    twelveWord.setEnabled(false);
                } else {
                    if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                        if (word1.equals(word2) || word1.equals(word3) || word2.equals(word3)) {
                            ToastUtils.showLongToast(context, getResources().getString(R.string.noNon_repeatable));
                        }
                        return;
                    } else {
                        if (ObjectUtils.isEmpty(wordOne.getText().toString())) {
                            wordOne.setText(elevenWord.getText().toString().trim());
                            wordOne.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordTwo.getText().toString())) {
                            wordTwo.setText(elevenWord.getText().toString().trim());
                            wordTwo.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordThree.getText().toString())) {
                            wordThree.setText(elevenWord.getText().toString().trim());
                            wordThree.setCompoundDrawables(null, null, icon_clear, null);
                        }
                        elevenWord.setBackground(getDrawable(R.drawable.shape_blue_bg));
                        elevenWord.setTextColor(getResources().getColor((R.color.white)));
                        elevenWord.setEnabled(false);
                    }
                }
                break;
            case R.id.twelve_word://  助记词  12
                if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                    oneWord.setEnabled(false);
                    twoWord.setEnabled(false);
                    threeWord.setEnabled(false);
                    fourWord.setEnabled(false);
                    fiveWord.setEnabled(false);
                    sixWord.setEnabled(false);
                    sevenWord.setEnabled(false);
                    eightWord.setEnabled(false);
                    nineWord.setEnabled(false);
                    tenWord.setEnabled(false);
                    elevenWord.setEnabled(false);
                    twelveWord.setEnabled(false);
                } else {
                    if (!ObjectUtils.isEmpty(word1) && !ObjectUtils.isEmpty(word2) && !ObjectUtils.isEmpty(word3)) {
                        if (word1.equals(word2) || word1.equals(word3) || word2.equals(word3)) {
                            ToastUtils.showLongToast(context, getResources().getString(R.string.noNon_repeatable));
                        }
                        return;
                    } else {
                        if (ObjectUtils.isEmpty(wordOne.getText().toString())) {
                            wordOne.setText(twelveWord.getText().toString().trim());
                            wordOne.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordTwo.getText().toString())) {
                            wordTwo.setText(twelveWord.getText().toString().trim());
                            wordTwo.setCompoundDrawables(null, null, icon_clear, null);
                        } else if (ObjectUtils.isEmpty(wordThree.getText().toString())) {
                            wordThree.setText(twelveWord.getText().toString().trim());
                            wordThree.setCompoundDrawables(null, null, icon_clear, null);
                        }
                        twelveWord.setBackground(getDrawable(R.drawable.shape_blue_bg));
                        twelveWord.setTextColor(getResources().getColor((R.color.white)));
                        twelveWord.setEnabled(false);
                    }
                }
                break;
            case R.id.next_step_btn:
                if (!word1.equals("")) {
                    if (!word2.equals("")) {
                        if (!word3.equals("")) {
                            String helps = word1 + "," + word2 + "," + word3;
                            showLoadingDialog();
                            mPresent.setHelp(context, key, helps);
                        } else {
                            ToastUtils.showShortToast(context, getResources().getString(R.string.please_check_three_word));
                        }
                    } else {
                        ToastUtils.showShortToast(context, getResources().getString(R.string.please_check_two_word));
                    }
                } else {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.please_check_one_word));
                }
                break;
        }
    }

    @Override
    protected SetHelpContact.SetHelpPresent createPresent() {
        return new SetHelpContact.SetHelpPresent();
    }

    @Override
    public void getSetHelpResult(Response<SetHelpResponse> response) {
        dismissLoadingDialog();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            Intent intent = new Intent(context, RegisterWalletMyActivity.class);
            intent.putExtra("wordone", wordOne.getText().toString());
            intent.putExtra("wordtwo", wordTwo.getText().toString());
            intent.putExtra("wordthree", wordThree.getText().toString());
            intent.putExtra("key", response.body().getData().getKeys());
            startActivity(intent);
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));

        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
        dismissLoadingDialog();
    }

    private boolean validate(String wordone, String wordtwo, String wordthree) {
        if (ObjectUtils.isEmpty(wordone.equals(""))) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.please_check_one_word));
            return true;
        }
        if (ObjectUtils.isEmpty(wordtwo.equals(""))) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.please_check_two_word));
            return true;
        }
        if (ObjectUtils.isEmpty(wordthree.equals(""))) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.please_check_three_word));
            return true;
        }
        return false;
    }
}
