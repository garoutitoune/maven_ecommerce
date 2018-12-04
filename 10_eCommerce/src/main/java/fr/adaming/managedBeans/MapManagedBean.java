package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean(name="mapMB")
public class MapManagedBean implements Serializable{
	
	private MapModel simpleModel;
	  
    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
          
        //Shared coordinates
        LatLng coord1 = new LatLng(47.213461, -1.561561);
          
        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Mistigris Nantes"));
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
	
	
	
	

}
