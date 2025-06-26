
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
  const intent = document.getElementById('emailModalIntent').value;
  const recipient = document.getElementById('emailModalRecipient').value;
  const messageBox = document.getElementById('emailModalMessage');

  if (!intent || !recipient) {
    messageBox.value = "Please enter both intent and recipient.";
    return;
  }

  fetch("/api/email/generate", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ recipientName: recipient, intent: intent })
  })
  .then(response => response.json())
  .then(data => {
    messageBox.value = data.message;
  })
  .catch(error => {
    messageBox.value = "Failed to generate email.";
    console.error(error);
  });
}


function sendEmailFromModal() {
  const recipient = document.getElementById('emailModalRecipient').value;
  const intent = document.getElementById('emailModalIntent').value;
  const message = document.getElementById('emailModalMessage').value;
  // TODO: Implement your send email logic here (AJAX or form submit)
  alert(`Email sent to ${recipient}!\n\nIntent: ${intent}\n\nMessage:\n${message}`);
  closeEmailModal();
}