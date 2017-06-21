package it.polimi.ingsw.cg30.model.util;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class Unmarshaller {
	/**
	 * this unmarshaller load from an xml files
	 * the entire file and returns the contenent into
	 * an object file
	 * @param path the file path of the file
	 * @return
	 */
	public static Object load(String path) {
		try {
		JAXBContext jc = JAXBContext.newInstance(Settings.class);
		javax.xml.bind.Unmarshaller u = jc.createUnmarshaller();
		return u.unmarshal(new File(path));
		} catch (JAXBException e) {
			Util.exception(e);
			return null;
		}
	}
}