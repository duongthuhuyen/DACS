/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package layout.user;

import dao.IAnswer;
import dao.IInfortest;
import dao.IQuestion;
import dao.ItestDetail;
import dao.impl.IAnswerImpl;
import dao.impl.IQuestionImpl;
import dao.impl.IinfortestImpl;
import dao.impl.ItestDetailImpl;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import layout.admin.Form_test;
//import static layout.create.test.content;
import pojo.Answer;
import pojo.InforTest;
import pojo.PointOfTest;
import pojo.Question;
import pojo.TestDetail;

/**
 *
 * @author PC
 */
public class Doing_test extends javax.swing.JFrame {

    /**
     * Creates new form Doing_test
     */
    private int idTest;
    private String nameTest;
    private String topicName;
    private int numberTest;
    private List<TestDetail> listTest;
    private int k;
    private List<Question> listQuestion = new ArrayList<>();
    private List<PointOfTest> listPointOfTests = new ArrayList<>();
    private List<Map<JRadioButton, Boolean>> listda = new ArrayList<>();
    private List<List<Answer>> listAnswer;
    private List<formLuaChon> listFormLuaChon = new ArrayList<>();
    JPanel box;

    public Doing_test(int idTest, String nameTest, String topicNam, List<TestDetail> listTest, int numberTest) {
        this.listTest = listTest;
        this.idTest = idTest;
        this.nameTest = nameTest;
        this.topicName = topicNam;
        this.numberTest = numberTest;
        this.listAnswer = new ArrayList<>();
        IQuestion iQuestion = new IQuestionImpl();
        //   load_question(0);

        if (listTest.size() > 0) {
            for (TestDetail t : listTest) {
                Optional<Question> q = iQuestion.getQuestionById(t.getQuestionId());
                Question question = q.orElseThrow();
                this.listQuestion.add(question);
            }
        }
        initComponents();//tôi thử xóa r nhưng ko đc nên thôi ko cần sửa nữa đâu v đc r 
        //center form
        content.setVisible(false);
        content.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        box = new JPanel();
        box.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        int heig = 0;
        for (int i = 0; i < numberTest; i++) {
            IAnswer iAnswer = new IAnswerImpl();
            List<Answer> listAnswers1 = iAnswer.getListAnswerByQId(listQuestion.get(i).getId());
            formLuaChon f = load_test(i, numberTest, this.listQuestion.get(i), listAnswers1);
            listFormLuaChon.add(f);
            heig = f.getHeight();
            //listPointOfTests.add(new PointOfTest(false,false));
        }

        box.setPreferredSize(new Dimension(content.getWidth()-25 , numberTest*280));
        box.setVisible(true);
        JScrollPane jScrollPane = new JScrollPane(box);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setPreferredSize(new Dimension(content.getWidth(), content.getHeight()));
        jScrollPane.setVisible(true);
        content.add(jScrollPane);
        content.setVisible(true);
        // System.out.println(jScrollPane.getHeight());
        k = 0;
        this.setLocationRelativeTo(null);
        jLabel_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("../../images/x.png")));

    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public String getNameTest() {
        return nameTest;
    }

    public void setNameTest(String nameTest) {
        this.nameTest = nameTest;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public PointOfTest getByQid(int k, formLuaChon f) {
        PointOfTest pointOfTest = new PointOfTest();
        boolean ch1 = f.getAnswer1().isSelected();
        boolean ch2 = f.getAnswer2().isSelected();
        boolean ch3 = f.getAnswer3().isSelected();
        boolean ch4 = f.getAnswer4().isSelected();
        if (!ch1 && !ch2 && !ch3 && !ch4) {
            pointOfTest.setChoose(false);
            pointOfTest.setCorrect(false);
        } else {
            pointOfTest.setChoose(true);
            if (listda.get(k).get(f.getAnswer1()) && ch1) {
                pointOfTest.setCorrect(true);
            } else if (listda.get(k).get(f.getAnswer2()) && ch2) {
                pointOfTest.setCorrect(true);
            } else if (listda.get(k).get(f.getAnswer3()) && ch3) {
                pointOfTest.setCorrect(true);
            } else if (listda.get(k).get(f.getAnswer4()) && ch4) {
                pointOfTest.setCorrect(true);
            } else {
                pointOfTest.setCorrect(false);
            }
        }
        return pointOfTest;
    }

    public Doing_test() {

    }

    public ArrayList<Integer> swap(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        int upper = n;
        for (int i = 0; i < n; ++i) {
            list.add(i);
        }

        ArrayList<Integer> result = new ArrayList();
        Random random = new Random();
        for (int i = 0; i < n; ++i) {
            int ind = random.nextInt(upper);
            result.add(list.get(ind));
            list.remove(ind);
            --upper;
        }

        return result;
    }

    public void loadData(formLuaChon fLC, List<Answer> lisst) {
        Map<JRadioButton, Boolean> map = new HashMap<>();
        ArrayList<Integer> listRandom = swap(4);
        map.put(fLC.getAnswer1(), lisst.get(listRandom.get(0)).isIsCorrect());
        fLC.getAnswer1().setText(lisst.get(listRandom.get(0)).getContent());
        map.put(fLC.getAnswer2(), lisst.get(listRandom.get(1)).isIsCorrect());
        fLC.getAnswer2().setText(lisst.get(listRandom.get(1)).getContent());
        map.put(fLC.getAnswer3(), lisst.get(listRandom.get(2)).isIsCorrect());
        fLC.getAnswer3().setText(lisst.get(listRandom.get(2)).getContent());
        map.put(fLC.getAnswer4(), lisst.get(listRandom.get(3)).isIsCorrect());
        fLC.getAnswer4().setText(lisst.get(listRandom.get(3)).getContent());
        this.listda.add(map);
        this.listAnswer.add(lisst);
    }

    public formLuaChon load_test(int k, int numberTest, Question question, List<Answer> lisst) {

      
        formLuaChon fLC = new formLuaChon(k, numberTest, question, lisst);
        fLC.getQContent().setText(question.getContent());
        fLC.getQName().setText("Question "+(k+1)+":");
        loadData(fLC, lisst);
        fLC.setVisible(true);
        box.add(fLC);
        
        return fLC;

    }

    public formLuaChon load_test1(int k, int numberTest, Question question, List<Answer> lisst) {

        content.setVisible(false);
        content.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JPanel box = new JPanel();
        box.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        formLuaChon fLC = new formLuaChon(k, numberTest, question, lisst);
        fLC.getQContent().setText(question.getContent());
        fLC.getQName().setText("Question "+(k+1));
        loadData(fLC, lisst);
        //1fLC.setVisible(true);
        box.add(fLC);
         box.setPreferredSize(new Dimension(content.getWidth()-25, (numberTest+1)/2*160));
        box.setVisible(true);
        JScrollPane jScrollPane = new JScrollPane(box);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setPreferredSize(new Dimension(content.getWidth(), content.getHeight() + 250));
        jScrollPane.setVisible(true);
        content.add(jScrollPane);
        content.setVisible(true);
        System.out.println(jScrollPane.getHeight());
        return fLC;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_close = new javax.swing.JLabel();
        jLabel_Minimize = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        content = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Doing Test");

        jLabel_close.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/x.png"))); // NOI18N
        jLabel_close.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseMoved(evt);
            }
        });
        jLabel_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseClicked(evt);
            }
        });

        jLabel_Minimize.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_Minimize.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Minimize.setText("-");
        jLabel_Minimize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jLabel_Minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_MinimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_MinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_MinimizeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_Minimize, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_close, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel_Minimize, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel_close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setBackground(new java.awt.Color(0, 255, 255));
        jLabel4.setForeground(new java.awt.Color(0, 153, 204));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("Topic: ");
        jLabel9.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Test: ");
        jLabel2.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel5.setText(this.nameTest);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel10.setText(this.topicName);

        jButton1.setBackground(new java.awt.Color(0, 255, 255));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Submit");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jScrollPane1.setToolTipText("");
        jScrollPane1.setInheritsPopupMenu(true);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(400, 400));

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setMaximumSize(new java.awt.Dimension(400, 400));
        jScrollPane1.setViewportView(content);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(111, 111, 111))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 339, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(134, 134, 134)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(48, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jLabel_closeMouseClicked

    private void jLabel_closeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseMoved
        // TODO add your handling code here:
        jLabel_close.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel_closeMouseMoved

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        System.out.println(listAnswer.size());
        System.out.println(listda.size());
        System.out.println(listQuestion.size());
        for (int i = 0; i < numberTest; i++) {
            listPointOfTests.add(getByQid(i, listFormLuaChon.get(i)));
        }
        // TODO add your handling code here:
        boolean checkDone = true;

        for (PointOfTest p : listPointOfTests) {
            if (!p.isChoose()) {
                checkDone = false;
            }
            System.out.println(p.isChoose());
        }
        if (checkDone) {
            int point = 0;

            for (PointOfTest p : listPointOfTests) {

                if (p.isCorrect()) {
                    point++;
                }
            }
            point = point*10/listQuestion.size();
            ResultTest resultTest = new ResultTest(this.nameTest, this.topicName, point, numberTest, this.idTest);
            resultTest.show();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Có câu chưa được chọn!",
                    "Thông báo",
                    JOptionPane.ERROR_MESSAGE);
        }
       
    }//GEN-LAST:event_jButton1MouseClicked

    private void jLabel_MinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_MinimizeMouseClicked
        // TODO add your handling code here:
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel_MinimizeMouseClicked

    private void jLabel_MinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_MinimizeMouseEntered
        // TODO add your handling code here:
        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
        jLabel_Minimize.setBorder(label_border);
    }//GEN-LAST:event_jLabel_MinimizeMouseEntered

    private void jLabel_MinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_MinimizeMouseExited
        // TODO add your handling code here:
        Border label_border = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black);
        jLabel_Minimize.setBorder(label_border);
    }//GEN-LAST:event_jLabel_MinimizeMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // jtextTen.setText("hi");

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            // jtextTen.setText("hi");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Doing_test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Doing_test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Doing_test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Doing_test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Doing_test().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Minimize;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
