
function openEmailModal(email) {
  document.getElementById('emailModalRecipient').value = '';
  document.getElementById('emailModalIntent').value = '';
  document.getElementById('emailModalMessage').value = '';
  document.getElementById('emailModal').classList.remove('hidden');
}

function closeEmailModal() {
  document.getElementById('emailModal').classList.add('hidden');
}

function generateEmailWithAI() {
  const intent = document.getElementById('emailModalIntent').value.trim();
  const recipient = document.getElementById('emailModalRecipient').value.trim();
  const messageBox = document.getElementById('emailModalMessage');

  if (!intent || !recipient) {
    messageBox.value = "⚠️ Please enter both intent and recipient.";
    return;
  }

  fetch("http://localhost:8081/api/email/generate", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      recipientName: recipient,
      intent: intent
    })
  })
    .then(response => {
      if (!response.ok) throw new Error("AI generation failed");
      return response.json();
    })
    .then(data => {
      messageBox.value = data.message || "No message generated.";
    })
    .catch(error => {
      messageBox.value = "❌ Failed to generate email.";
      console.error("AI Error:", error);
    });
}
function sendEmailFromModal() {
  const recipient = document.getElementById('emailModalRecipient').value.trim();
  const subject = document.getElementById('emailModalIntent').value.trim() || "No Subject";
  const body = document.getElementById('emailModalMessage').value.trim();

  if (!recipient || !body) {
    alert("❗ Please fill recipient and message before sending.");
    return;
  }

  fetch("http://localhost:8081/api/email/send", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      to: recipient,
      subject: subject,
      body: body
    })
  })
    .then(response => {
      if (!response.ok) throw new Error("Sending failed");
      return response.text();
    })
    .then(msg => {
      alert(msg || "✅ Email sent!");
      closeEmailModal();
    })
    .catch(error => {
      alert("❌ Failed to send email.");
      console.error("Email Error:", error);
    });
}
