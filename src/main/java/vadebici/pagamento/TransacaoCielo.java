package vadebici.pagamento;

public class TransacaoCielo 
{
	public String MerchantOrderId;
	public Customer Customer;
	public Pagamento Payment;
	
	public TransacaoCielo(String MerchantOrderId)
	{
		this.Customer        = new Customer();
		this.MerchantOrderId = MerchantOrderId;
		this.Payment         = new Pagamento();
	}
	
	public class Customer
	{
	      public String Name;
	}
}
