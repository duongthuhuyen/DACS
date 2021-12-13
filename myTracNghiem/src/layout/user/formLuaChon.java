/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package layout.user;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import pojo.Answer;
import pojo.PointOfTest;
import pojo.Question;

/**
 *
 * @author PC
 */
public class formLuaChon extends javax.swing.JPanel {

    /**
     * Creates new form formLuaChon
     */
    private int k;
   // private List<Map<JRadioButton,Boolean>> listda = new ArrayList<>();
    //private String 
    private int numberTest;
   // private 
    private Question question;
    private List<Answer> lisst;
    private Map<JRadioButton,Boolean> map;
     ArrayList<Integer> listRandom ;
    public formLuaChon() {
        //initComponents();
    }

    public formLuaChon(int k, int numberTest, Question question, List<Answer> lisst) {
        this.k = k;
        this.numberTest = numberTest;
        this.question = question;
        this.lisst = lisst;
        this.map = new HashMap<>();
        this.listRandom = swap(4);
        initComponents();
    }

    public JLabel getQName() {
        return QName;
    }

    public JPanel getjPanel3() {
        return jPanel3;
    }
    

    public JRadioButton getAnswer1() {
        return answer1;
    }

    public JRadioButton getAnswer2() {
        return answer2;
    }

    public JRadioButton getAnswer3() {
        return answer3;
    }

    public JRadioButton getAnswer4() {
        return answer4;
    }

    public void setAnswer1(JRadioButton answer1) {
        this.answer1 = answer1;
    }

    public void setAnswer2(JRadioButton answer2) {
        this.answer2 = answer2;
    }

    public void setAnswer3(JRadioButton answer3) {
        this.answer3 = answer3;
    }

    public void setAnswer4(JRadioButton answer4) {
        this.answer4 = answer4;
    }

    public JLabel getQContent() {
        return QContent;
    }

    public void setQContent(JLabel QContent) {
        this.QContent = QContent;
    }
    
    

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getNumberTest() {
        return numberTest;
    }

    public void setNumberTest(int numberTest) {
        this.numberTest = numberTest;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getLisst() {
        return lisst;
    }

    public void setLisst(List<Answer> lisst) {
        this.lisst = lisst;
    }

    public Map<JRadioButton, Boolean> getMap() {
        
        return map;
    }

    public ArrayList<Integer> getListRandom() {
        return listRandom;
    }
      public ArrayList<Integer> swap(int n){
        ArrayList<Integer> list = new ArrayList<>();
        int upper = n;
        for(int i=0; i<n; ++i){
            list.add(i);
        }

        ArrayList<Integer> result = new ArrayList();
        Random random = new Random();
        for (int i=0; i<n; ++i){
            int ind = random.nextInt(upper);
            result.add(list.get(ind));
            list.remove(ind);
            --upper;
        }

        return result;
    }

    public void setMap() {
       // ArrayList<Integer> listRandom = swap(4);
      map = new HashMap<JRadioButton, Boolean>();
        map.put(answer1, this.lisst.get(listRandom.get(0)).isIsCorrect());
       // this.answer1.setText(this.lisst.get(listRandom.get(0)).getContent());
        map.put(answer2, this.lisst.get(listRandom.get(1)).isIsCorrect());
       // this.answer2.setText(this.lisst.get(listRandom.get(1)).getContent());
        map.put(answer2, this.lisst.get(listRandom.get(2)).isIsCorrect());
       // this.answer3.setText(this.lisst.get(listRandom.get(2)).getContent());
        map.put(answer4, this.lisst.get(listRandom.get(3)).isIsCorrect());
       // this.answer4.setText(this.lisst.get(listRandom.get(3)).getContent());
    }
   
  /*  
     public PointOfTest getByQid(int qid){
        PointOfTest pointOfTest = new PointOfTest();
        boolean ch1 = answer1.isSelected();
        boolean ch2 = answer2.isSelected();
        boolean ch3 = answer3.isSelected();
         boolean ch4 = answer4.isSelected();
         if(!ch1&&!ch2&&!ch3&&!ch4){
             pointOfTest.setChoose(false);
             pointOfTest.setCorrect(false);
         }else{
             pointOfTest.setChoose(true);
           if(listda.get(qid).get(answer1)&&ch1){
               pointOfTest.setCorrect(true);
           }else if(listda.get(qid).get(answer2)&&ch2){
               pointOfTest.setCorrect(true);
           }else if(listda.get(qid).get(answer3)&&ch3)
           {
               pointOfTest.setCorrect(true);
           }else if(listda.get(qid).get(answer4)&&ch4){
               pointOfTest.setCorrect(true);
           }else{
               pointOfTest.setCorrect(false);
           }
        }
        return pointOfTest;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        QName = new javax.swing.JLabel();
        QContent = new javax.swing.JLabel();
        answer1 = new javax.swing.JRadioButton();
        answer2 = new javax.swing.JRadioButton();
        answer3 = new javax.swing.JRadioButton();
        answer4 = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        QName.setBackground(new java.awt.Color(255, 255, 255));
        QName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        QName.setText("Question 1:");

        QContent.setBackground(new java.awt.Color(255, 255, 255));
        QContent.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        QContent.setText("Q");

        buttonGroup1.add(answer1);
        answer1.setText("1");

        buttonGroup1.add(answer2);
        answer2.setText("2");

        buttonGroup1.add(answer3);
        answer3.setText("3");

        buttonGroup1.add(answer4);
        answer4.setText("4");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(QName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(QContent, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(answer2)
                            .addComponent(answer1)
                            .addComponent(answer3)
                            .addComponent(answer4))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QName)
                    .addComponent(QContent))
                .addGap(32, 32, 32)
                .addComponent(answer1)
                .addGap(18, 18, 18)
                .addComponent(answer2)
                .addGap(18, 18, 18)
                .addComponent(answer3)
                .addGap(18, 18, 18)
                .addComponent(answer4)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

     public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setBounds(0, 0, 500, 500);
        jFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        formLuaChon form = new formLuaChon();
        form.setVisible(true);
        form.setBackground(Color.red);
        jFrame.add(form);
        jFrame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel QContent;
    private javax.swing.JLabel QName;
    private javax.swing.JRadioButton answer1;
    private javax.swing.JRadioButton answer2;
    private javax.swing.JRadioButton answer3;
    private javax.swing.JRadioButton answer4;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
