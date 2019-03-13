Class {
	#name : #MMCMooseAssociationFormPresenter,
	#superclass : #MMCMooseAbstractQueryPresenter,
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #examples }
MMCMooseAssociationFormPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #accessing }
MMCMooseAssociationFormPresenter >> name [
	^ 'Association'
]
