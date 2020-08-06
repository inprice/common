package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import io.inprice.common.meta.SubsEvent;
import io.inprice.common.meta.SubsSource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubsTrans implements Serializable {

  private static final long serialVersionUID = -4787008755878198572L;

  private Long id;
  private Long companyId;  // 1
  private SubsSource source;  // SUBSCRIPTION, COUPON
  private String eventId;  // evt_1HBOzGBiHTcqawyMeOM8iaYx
  private SubsEvent event;    // invoice.payment_succeeded
  private Boolean successful = Boolean.FALSE;
  private String reason;   // subscription_create
  private String description; // 1 × Micro Plan (at €5.00 / month)
  private String fileUrl;  // "hosted_invoice_url": "https://pay.stripe.com/invoice/acct_1H0m4UBiHTcqawyM/invst_HkuoYwDhzPwLGBLKwUEWUhOH9tTx31S"
  private Date createdAt;

}
