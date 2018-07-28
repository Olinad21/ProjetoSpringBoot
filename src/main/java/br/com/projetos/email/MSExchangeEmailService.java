package br.com.projetos.email;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;

import java.util.Date;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import java.util.logging.Level;

import java.util.logging.Logger;

import org.joda.time.DateTime;

import antlr.Parser;
import microsoft.exchange.webservices.data.autodiscover.IAutodiscoverRedirectionUrl;

import microsoft.exchange.webservices.data.core.ExchangeService;

import microsoft.exchange.webservices.data.core.PropertySet;

import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;

import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.search.LogicalOperator;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;

import microsoft.exchange.webservices.data.core.service.folder.CalendarFolder;

import microsoft.exchange.webservices.data.core.service.folder.Folder;

import microsoft.exchange.webservices.data.core.service.item.Appointment;

import microsoft.exchange.webservices.data.core.service.item.EmailMessage;

import microsoft.exchange.webservices.data.core.service.item.Item;

import microsoft.exchange.webservices.data.core.service.schema.AppointmentSchema;

import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;

import microsoft.exchange.webservices.data.credential.WebCredentials;

import microsoft.exchange.webservices.data.property.complex.ItemId;

import microsoft.exchange.webservices.data.search.CalendarView;

import microsoft.exchange.webservices.data.search.FindItemsResults;

import microsoft.exchange.webservices.data.search.ItemView;

import microsoft.exchange.webservices.data.search.filter.SearchFilter;

public class MSExchangeEmailService {

	private String DOMAIN = "danilo.oliveira@caixaseguradora.com.br";

	private String USER = "EST11577";

	private String PASS = "Caixa123";

	private String body;

	private List<StoreMail> listStoreMail = new ArrayList<>();

	private StoreMail storeMail = new StoreMail();

	List msgDataList = new ArrayList<String>();

	public static class RedirectionUrlCallback implements IAutodiscoverRedirectionUrl {

		public boolean autodiscoverRedirectionUrlValidationCallback(String redirectionUrl) {

			return redirectionUrl.toLowerCase().startsWith("https://");

		}

	}

	private static ExchangeService service;

	private static Integer NUMBER_EMAILS_FETCH = 200; // somente os últimos 5 e-mails / compromissos são buscados.

	/**
	 *
	 * 
	 * 
	 * Primeiro, verifique se "https://webmail.xxxx.com/ews/Services.wsdl" e
	 * 
	 * "https://webmail.xxxx.com/ews/Exchange.asmx"
	 *
	 * 
	 * 
	 * está acessível, se sim, significa que o serviço Web do Exchange está ativado
	 * 
	 * no seu MS Exchange.
	 *
	 * 
	 * 
	 */

	static {

		try {

			service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);

			// service.setUrl(new URI("https://webmail.xxxx.com/ews/Exchange.asmx"));

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	/**
	 * Inicialize as credenciais do Exchange.
	 *
	 * Não se esqueça de substituir as variáveis "USRNAME", "PWD", "DOMAIN_NAME".
	 *
	 */

	public MSExchangeEmailService() {

		service.setCredentials(new WebCredentials(USER, PASS));

		try {

			service.autodiscoverUrl(DOMAIN, new RedirectionUrlCallback());

		} catch (Exception ex) {

			Logger.getLogger(MSExchangeEmailService.class.getName()).log(Level.SEVERE, null, ex);

		}

		service.setTraceEnabled(true);

	}

	/**
	 *
	 * 
	 * 
	 * Lendo um email de cada vez. Usando o ID do item do email.
	 *
	 * 
	 * 
	 * Criando um mapa de dados da mensagem como um valor de retorno.
	 *
	 * 
	 * 
	 */

	public Map readEmailItem(ItemId itemId) {

		Map messageData = new HashMap();

		try {

//                     

			Item itm = Item.bind(service, itemId, PropertySet.FirstClassProperties);

			EmailMessage emailMessage = EmailMessage.bind(service, itm.getId());

			messageData.put("emailItemId", emailMessage.getId().toString());

			messageData.put("subject", emailMessage.getSubject().toString());

			messageData.put("fromAddress", emailMessage.getFrom().getAddress().toString());

			messageData.put("senderName", emailMessage.getSender().getName().toString());

			Date dateTimeCreated = emailMessage.getDateTimeCreated();

			messageData.put("SendDate", dateTimeCreated.toString());

			Date dateTimeRecieved = emailMessage.getDateTimeReceived();

			messageData.put("RecievedDate", dateTimeRecieved.toString());

			messageData.put("Size", emailMessage.getSize() + "");

//                  messageData.put("Body", emailMessage.getBody());
			
			storeMail.setData(dateTimeCreated);
			storeMail.setSubject(emailMessage.getSubject().toString());

			this.body = emailMessage.getBody().toString();
			
			if (body != null) {

				storeMail.setCertificado(obtem("Certificado:"));

				storeMail.setCpf(obtem("CPF:"));

				storeMail.setTipo(obtem("Tipo:"));				

				storeMail.setValorContribuicao(obtem("Contribuía com:"));					

				System.out.println("\n");

				listStoreMail.add(storeMail);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return messageData;

	}

	/**
	 *
	 * 
	 * 
	 * O número de e-mail que queremos ler é definido como NUMBER_EMAILS_FETCH,
	 * @throws ParseException 
	 *
	 * 
	 * 
	 */

	public List<?> readEmails()  {

		List msgDataList = new ArrayList<String>();
		
		Calendar now = Calendar.getInstance();
		
		String hoje = Calendar.getInstance().getTime().toString();
		
		Date hj = Calendar.getInstance().getTime();
		
		  now.set(2018, Calendar.JULY, Calendar.getInstance().DAY_OF_MONTH);
	      
		  Date data = now.getTime();
		  
		  Date dx = new Date();
		  
		  
		  int dia = dx.getDay();
		  int mes = Calendar.getInstance().MONTH;
		  int ano = Calendar.getInstance().YEAR;
		
		  String d = Integer.toString(dia);
		Date endDate = now.getTime();
	try {	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("dia"+dia);
		
		
		String diaa = hoje.substring(8,10);
		String anoo =  hoje.substring(24, hoje.length());
		
		String dataHoje = diaa.concat("/07/"+anoo);
		System.out.println("data de hoje "+dataHoje);
		System.out.println("E-MAILS");
	
		//Date d = sdf.parse(Calendar.getInstance().getTime().toString());
		
		

		

			Folder folder = Folder.bind(service, WellKnownFolderName.Inbox);

			SearchFilter searchFilterTipo = new SearchFilter.ContainsSubstring(ItemSchema.Subject,
			
					"Ativação / Suspensão de Contribuições");
			
			
			SearchFilter searchFilterDate = 
					   new SearchFilter.IsGreaterThan(ItemSchema.DateTimeReceived, hj);
			
			SearchFilter searchFilterAll =  new SearchFilter.SearchFilterCollection(LogicalOperator.And, searchFilterTipo,searchFilterDate);
			

			FindItemsResults<Item> results = service.findItems(folder.getId(), searchFilterDate,

					new ItemView(NUMBER_EMAILS_FETCH));

			int i = 1;

			for (Item item : results) {

				Map<String, Object> messageData = new HashMap<String, Object>();

				messageData = readEmailItem(item.getId());

//                         System.out.println("\nEmails #" + (i++) + ":");

//

//                         System.out.println("subject : " + messageData.get("subject").toString());

//

//                         System.out.println("Sender : " + messageData.get("senderName").toString());

//

//                         System.out.println("Body: ");

				// body = messageData.get("Body").toString();

			

				for (StoreMail obj : listStoreMail) {

					System.out.println("subject: " + obj.getSubject());

					System.out.println("Cpf: " + obj.getCpf());

					System.out.println("Certificado: " + obj.getCertificado());

					System.out.println("Contribuição: " + obj.getValorContribuicao());

					System.out.println("Tipo: " + obj.getTipo());

					System.out.println("Data: " + obj.getData());

					System.out.println("\n");

				}

				// msgDataList.add(messageData);

//                         msgDataList.add(storeMail.gget);

//                         msgDataList.add(CPF);

//                         msgDataList.add(TIPO);

			}

//                  for(int k=0; k< msgDataList.size();k++) {

//                         System.out.println(msgDataList.get(k));

//                  }

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("Tamanho da lista: " + listStoreMail.size());

		return msgDataList;

	}

	public String obtem(String str) {

		String strValue = null;

		String body = this.body;

		if (body.contains(str)) {

			// obtem o corpo onde começa a String

			String StringBody = body.substring(body.indexOf(str), body.length());

			// obtem o inicio do VALOR referente a String

			String str1 = StringBody.substring(StringBody.indexOf("#9B9591\">") + 9, StringBody.length());

			int ocoorencia = str1.indexOf("</span>");

			strValue = str1.substring(0, ocoorencia);

//                  System.out.println(str + strValue);

//          

		}

		return strValue;

	}

	/**
	 *
	 * 
	 * 
	 * Lendo um compromisso de cada vez. Usando o ID do compromisso do email.
	 *
	 * 
	 * 
	 * Criando um mapa de dados da mensagem como um valor de retorno.
	 *
	 * 
	 * 
	 */

	public Map readAppointment(Appointment appointment) {

		Map appointmentData = new HashMap();

		try {

			appointmentData.put("appointmentItemId", appointment.getId().toString());

			appointmentData.put("appointmentSubject", appointment.getSubject());

			appointmentData.put("appointmentStartTime", appointment.getStart() + "");

			appointmentData.put("appointmentEndTime", appointment.getEnd() + "");

			// appointmentData.put("appointmentBody", appointment.getBody().toString());

		} catch (ServiceLocalException e) {

			e.printStackTrace();

		}

		return appointmentData;

	}

	/**
	 *
	 * 
	 * 
	 * O número de compromissos que queremos ler é definido como
	 * 
	 * NUMBER_EMAILS_FETCH,
	 *
	 * 
	 * 
	 * Aqui eu também considerei os dados iniciais e a data final, que é um período
	 * 
	 * de 30 dias.
	 *
	 * 
	 * 
	 * Precisamos definir a propriedade CalendarView dependendo da necessidade da
	 * 
	 * nossa.
	 *
	 * 
	 * 
	 */

	public List readAppointments() {

		List apntmtDataList = new ArrayList();

		Calendar now = Calendar.getInstance();

		Date startDate = Calendar.getInstance().getTime();

		now.add(Calendar.DATE, 30);

		Date endDate = now.getTime();

		try {

			CalendarFolder calendarFolder = CalendarFolder.bind(service, WellKnownFolderName.Calendar,

					new PropertySet());

			CalendarView cView = new CalendarView(startDate, endDate, 10);

			cView.setPropertySet(

					new PropertySet(AppointmentSchema.Subject, AppointmentSchema.Start, AppointmentSchema.End));// we

						FindItemsResults<Appointment> appointments = calendarFolder.findAppointments(cView);

			System.out.println("|------------------> Appointment count = " + appointments.getTotalCount());

			int i = 1;

			// List appList = appointments.getItems();

			for (Appointment appointment : appointments.getItems()) {

				System.out.println("\nAPPOINTMENT #" + (i++) + ":");

				Map appointmentData = new HashMap();

				appointmentData = readAppointment(appointment);

				System.out.println("subject : " + appointmentData.get("appointmentSubject").toString());

				System.out.println("On : " + appointmentData.get("appointmentStartTime").toString());

				apntmtDataList.add(appointmentData);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return apntmtDataList;

	}

	public static void getAllMeetings() throws Exception {

		try {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = formatter.parse("2016-01-01 00:00:00");

			SearchFilter filter = new SearchFilter.IsGreaterThanOrEqualTo(ItemSchema.LastModifiedTime, startDate);

			FindItemsResults<Item> findResults = service.findItems(WellKnownFolderName.Calendar, filter,

					new ItemView(1000));

			System.out.println("|------------------> meetings count = " + findResults.getTotalCount());

			for (Item item : findResults.getItems())

			{

				Appointment appt = (Appointment) item;

				// appt.setStartTimeZone();

				System.out.println("TimeZone=====" + appt.getTimeZone());

				System.out.println("SUBJECT=====" + appt.getSubject());

				System.out.println("Location========" + appt.getLocation());

				System.out.println("Start Time========" + appt.getStart());

				System.out.println("End Time========" + appt.getEnd());

				System.out.println("Email Address========" + appt.getOrganizer().getAddress());

				System.out.println("Last Modified Time========" + appt.getLastModifiedTime());

				System.out.println("*************************************************\n");

			}

		} catch (Exception exp) {

			exp.printStackTrace();

		}

	}

	public static void main(String[] args) {

		MSExchangeEmailService msees = new MSExchangeEmailService();

		msees.readEmails();

		// msees.readAppointments();

		try {

			// msees.getAllMeetings();

		} catch (Exception ex) {

			System.out.println(ex.getMessage());

			// Logger.getLogger(MSExchangeEmailService.class.getName()).log(Level.SEVERE,

			// null, ex);

		}

	}

}
