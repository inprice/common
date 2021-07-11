package io.inprice.common.meta;

/**
 * InvitationStatus: when a user (invitor) invites another user (invitee)
 * first status of an invitation is PENDING
 * if the invitee accepts the invitation than status becomes JOINED
 * the invitee may leave from the account then status becomes LEFT
 * 
 */
public enum UserStatus {

   PENDING, //default status
   JOINED, //member accepted

   REJECTED, //member action without accepting invitation
   LEFT, //member action after accepting invitation

   PAUSED, //admin action
   DELETED; //admin action. members remain in this status for more 3 hours before permanently deleted!

}