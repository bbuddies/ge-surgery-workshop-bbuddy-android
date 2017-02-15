class EditAccountPage < PageBase

  def marked
    'Update'
  end

  def edit_account(account)
    clear_then_enter_text('name', account.name)
    clear_then_enter_text('balanceBroughtForward', account.balance)
    touch('Update')
  end

  def delete_account(account)
    touch('Delete')
  end

end