package com.impactflux.terracraft.network;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SerializationContext
{
	public ArrayList<ClassSerializer> idToClass = new ArrayList<ClassSerializer> ();
	public Map<String, Integer> classToId = new TreeMap<String, Integer>();

}
