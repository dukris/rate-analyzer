INSERT INTO analyzer.preferences (id, user_id, currency, rate, date)
VALUES (gen_random_uuid(), '967a7ce6-2b47-4a9f-bde1-78401509e82d', 'USDT', 1, '2024-02-28 12:00:00')
ON CONFLICT DO NOTHING;