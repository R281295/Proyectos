package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UtilJSON {
	
	
	private JSONObject getJSON() {
		JSONObject json = null;
		try {
			FileReader file = new FileReader(Constantes.JSON);
			BufferedReader br = new BufferedReader(file);
			
			String contenido = "";
			String linea = "";
			while((linea = br.readLine()) != null) {
				contenido += linea + "\n";
			}
			
			br.close();
			file.close();
			
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(contenido);
			json = (JSONObject)obj;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo leer el JSON:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return json;
	}
	
	
	@SuppressWarnings("unchecked")
	public void incrementarJugadas() {
		JSONObject json = getJSON();
		if(json != null) {
			int jugadas = Integer.parseInt(json.get("Jugadas").toString());
			json.put("Jugadas", ++jugadas);
		}
		actualizarJSON(json);
	}

	@SuppressWarnings("unchecked")
	public void incrementarPerdidas() {
		JSONObject json = getJSON();
		if(json != null) {
			int jugadas = Integer.parseInt(json.get("Perdidas").toString());
			json.put("Perdidas", ++jugadas);
		}
		actualizarJSON(json);
	}
	
	@SuppressWarnings("unchecked")
	public void incrementarGanadas() {
		JSONObject json = getJSON();
		if(json != null) {
			int jugadas = Integer.parseInt(json.get("Ganadas").toString());
			json.put("Ganadas", ++jugadas);
		}
		actualizarJSON(json);
	}
	
	@SuppressWarnings("unchecked")
	public void incrementarSinAcabar() {
		JSONObject json = getJSON();
		if(json != null) {
			int jugadas = Integer.parseInt(json.get("Jugadas").toString());
			int perdidas = Integer.parseInt(json.get("Perdidas").toString());
			int ganadas = Integer.parseInt(json.get("Ganadas").toString());
			int sinAcabar = jugadas - (ganadas + perdidas);
			json.put("SinAcabar", sinAcabar);
		}
		actualizarJSON(json);
	}
	
	private void actualizarJSON(JSONObject json) {
		try {
			Files.write(Paths.get(Constantes.JSON), json.toJSONString().getBytes());
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo actualizar el JSON:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String getParam(String param) {
		return getJSON().get(param).toString();
	}

}
