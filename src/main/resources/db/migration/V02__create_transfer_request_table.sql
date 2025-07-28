create table TransferRequest(
  id    UUID not null primary key,
  wallet_id  UUID not null,
  recipient_id UUID not null,
  amount DECIMAL not null,
  created_at timestamptz not null,
  updated_at timestamptz,
  executed_at timestamptz,
  rejected_at timestamptz
);