package ca.ulaval.glo4002.mockexercise;

import org.junit.jupiter.api.BeforeEach;

public class StartByTestingThisTest {

  private StartByTestingThis service;

  @Mock private CartFactory cartFactory;

  @Mock private ProductRepository productRepository;

  @Mock private Cart cart;

  @Mock private Product product;

  @BeforeEach
  public void setUp() {
    service = new StartByTestingThis(cartFactory, productRepository);
  }

  @Test
  public void givenProduct_whenInstantBuy_thenInvoiceIsReturned() {
    String clientEmail = RandomString.make();
    String productSku = RandomString.make();
    when(cartFactory.create(clientEmail)).thenReturn(cart);
    when(productRepository.findBySku(productSku)).thenReturn(product);

    Invoice invoice = service.oneClickBuy(clientEmail, productSku);

    Assertions.assertNotNull(invoice);
  }
  
  @Test
  public void givenNoneExistentProduct_whenInstantBuy_ThenExceptionIsRaised(){
    String clientEmail = RandomString.make();
    String productSku = RandomString.make();
    when(cartFactory.create(clientEmail)).thenReturn(cart);
    when(productRepository.findBySku(productSku)).thenReturn(null);
    
   
    Assertions.assertThrows(ProductNotFoundException.class, () -> service.oneClickBuy(clientEmail, productSku) );
  }
  
}
