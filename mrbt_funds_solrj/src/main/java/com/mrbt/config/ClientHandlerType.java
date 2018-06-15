package com.mrbt.config;

public class ClientHandlerType {
	public static enum DeteleType {
		NAME("NAME"), CODE("CODE");
		protected String type;

		private DeteleType(String type) {
			this.type = type;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	}
}
