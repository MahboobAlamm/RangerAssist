FROM python:3.10-slim
WORKDIR /WareLobbyApplication
RUN pip install --no-cache-dir flask pymongo
# Copy all project files into container
COPY . .
EXPOSE 5000
CMD ["python", "run.py"]