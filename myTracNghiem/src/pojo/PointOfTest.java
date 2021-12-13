/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author PC
 */
public class PointOfTest {
    private boolean choose;
    private boolean correct;

    public PointOfTest(boolean choose, boolean correct) {
        this.choose = choose;
        this.correct = correct;
    }
    

    public boolean isChoose() {
        return choose;
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public PointOfTest() {
    }
    
    
}
