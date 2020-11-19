/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;



/**
 *
 * @author cphtk
 */
public class InHouse extends Part {

    int machineId;

    public InHouse() {
           this.id = 0;
        this.name = "";
        this.price = 0.0;
        this.stock = 0;
        this.min = 0;
        this.max = 0;
        this.machineId = 0;
    }

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        setId(id);
       setName(name);
       setPrice(price);
       setStock(stock);
       setMin(min);
       setMax(max);
       setMachineId(machineId);
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMachineId() {
        return this.machineId;
    }

}
