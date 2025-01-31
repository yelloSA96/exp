
import yfinance as yf
import matplotlib.pyplot as plt

nvda_data = yf.download('NVDA', start='2022-01-01', end='2022-12-31')['Close']
tesla_data = yf.download('TSLA', start='2022-01-01', end='2022-12-31')['Close']
fig, ax1 = plt.subplots()

ax1.plot(nvda_data.index, nvda_data.values, label='NVDA')
ax1.plot(tesla_data.index, tesla_data.values, label='TSLA')

ax1.legend(loc='upper left')
plt.xlabel('Date')
plt.ylabel('Price (USD)')
plt.title('NVIDIA (NVDA) and TESLA (TSLA) Stock Price Change YTD')
plt.show()
